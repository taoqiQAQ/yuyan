package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //处理所有声音文件的播放
    private MediaPlayer mMediaPlayer;

    //播放声音文件时处理音频对焦
    private AudioManager mAudioManager;

    /**
     * 只要音频焦点发生变化（即，由于其他应用或设备，我们获得或失去音频焦点），
     * 此监听器就会触发。
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // AUDIOFOCUS_LOSS_TRANSIENT情况意味着我们在短时间内丢失了音频焦点。
                // AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK案例意味着我们的应用允许继续播放声音，但音量较低。
                // 我们将以相同的方式对待这两种情况，因为我们的应用程序播放短的声音文件。

                //暂停播放并将播放器重置为文件的开头。
                // 这样，我们可以从开始播放时，我们恢复播放。
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // AUDIOFOCUS_GAIN情况意味着我们已经恢复了焦点并可以恢复播放。
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // AUDIOFOCUS_LOSS案例意味着我们失去了音频焦点，
                // 停止播放和清理资源
                releaseMediaPlayer();
            }
        }
    };

    /**
     * 当{@link MediaPlayer}完成播放音频文件时，会触发此侦听器。
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // 现在声音文件已经播放完毕，释放媒体播放器资源。
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //创建并设置{@link AudioManager}以请求音频焦点
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//        //创建Words数组
//        String[] Words = new String[10];
//        Words[0] = "one";
//        Words[1] = "two";
//        Words[2] = "three";
//        Words[3] = "four";
//        Words[4] = "five";
//        Words[5] = "six";
//        Words[6] = "seven";
//        Words[7] = "eight";
//        Words[8] = "nine";
//        Words[9] = "ten";
//
//        Log.v("NumbersActivity","Word at index 0: " + Words[0]);
//        Log.v("NumbersActivity","Word at index 1: " + Words[1]);

        //创建ArrayList：我要添加String元素，所以我使它的字符串类型
        final ArrayList<Word> words = new ArrayList<Word>();
        //这是元素应如何添加到数组列表
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        //ListView

        //创建一个{@link WordAdapter}，其数据源是一个{@link Word}的列表。
        //适配器知道如何为列表中的每个项目创建列表项。
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        //在{@link Activity}的视图层次结构中查找{@link ListView}对象。
        //应该有一个{@link ListView}，视图ID称为list，它在中声明
        // word_list.xml布局文件。
        ListView listView = (ListView) findViewById(R.id.list);
        //使{@link ListView}使用我们在上面创建的{@link WordAdapter}，以便
        // {@link ListView}将显示列表中每个{@link Word}的列表项。
        listView.setAdapter(adapter);

        //设置点击监听器以在单击列表项时播放音频
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {

                //在用户点击的给定位置获取{@link Word}对象
                Word word = words.get(position);

                //如果媒体播放器目前存在，请释放它，因为我们即将播放不同的声音文件
                releaseMediaPlayer();

                // 请求音频焦点，以便播放音频文件。
                // 该应用程序需要播放一个短的音频文件，
                // 所以我们将请求音频焦点与短时间与AUDIOFOCUS_GAIN_TRANSIENT。
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // 使用音乐流。
                        AudioManager.STREAM_MUSIC,
                        // 请求永久性焦点。
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // 现在已经具有Audio Focus 了

                    //为与当前字相关联的音频资源创建并设置{@link Media Player}
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    //启动音频文件
                    mMediaPlayer.start(); // no need to call prepare(); create() does that for you

                    // 在媒体播放器上设置监听器，以便在声音播放完毕后，
                    // 我们可以停止并释放媒体播放器。
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

//    //GridView
//         /*
//        创建一个{@link ArrayAdapter}，其数据源是一个字符串列表。
//        适配器知道如何使用Android框架中定义的simple_list_item_1.xml布局资源
//        为列表中的每个项目创建布局。
//        此列表项布局包含一个{@link TextView}，适配器将设置为显示单个字。
//         */
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//
//        //在{@link Activity}的视图层次结构中查找{@link ListView}对象。
//        //应该有一个{@link ListView}，其视图ID称为list，
//        //它在activity_numbers.xml布局文件中声明。
//        GridView listView = (GridView) findViewById(R.id.list);
//
//        //使{@link ListView}使用我们在上面创建的{@link ArrayAdapter}，
//        //以便{@link ListView}将显示单词列表中每个字词的列表项。
//        //通过调用{@link ListView}对象上的setAdapter方法并传入1个参数
//        //（即具有变量名为itemsAdapter的{@link ArrayAdapter}）来执行此操作。
//        listView.setAdapter(itemsAdapter);


//        /*
//        创建一个{@link ArrayAdapter}，其数据源是一个字符串列表。
//        适配器知道如何使用Android框架中定义的simple_list_item_1.xml布局资源
//        为列表中的每个项目创建布局。
//        此列表项布局包含一个{@link TextView}，适配器将设置为显示单个字。
//         */
//        ArrayAdapter<Word> itemsAdapter =
//                new ArrayAdapter<Word>(this, R.layout.list_item , words);

//        //在{@link Activity}的视图层次结构中查找{@link ListView}对象。
//        //应该有一个{@link ListView}，其视图ID称为list，
//        //它在activity_numbers.xml布局文件中声明。
//        ListView listView = (ListView) findViewById(R.id.list);
//
//        //使{@link ListView}使用我们在上面创建的{@link ArrayAdapter}，
//        //以便{@link ListView}将显示单词列表中每个字词的列表项。
//        //通过调用{@link ListView}对象上的setAdapter方法并传入1个参数
//        //（即具有变量名为itemsAdapter的{@link ArrayAdapter}）来执行此操作。
//        listView.setAdapter(itemsAdapter);

//        //通过将每个元素打印到日志中来验证列表的内容
//        Log.v("NumbersActivity", "word at index 0: " + words.get(0));
//        Log.v("NumbersActivity", "word at index 1: " + words.get(1));
//        Log.v("NumbersActivity", "word at index 2: " + words.get(2));


//        //找到rootView的LinearLayout
//        //将其储存在叫rootView的变量中
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//
//      //用for循环
//        for (int index = 0; index < words.size(); index++) {
//
//            //创建一个新的TextView
//            TextView wordView = new TextView(this);
//
//            // 将文本设置为当前索引处的字
//            wordView.setText(words.get(index));
//
//            // 将此TextView作为另一个子项添加到此布局的根视图
//            rootView.addView(wordView);
//        }


        //用while循环
//        //创建一个变量以跟踪当前索引位置
//        int index = 0;
//        /*
//        保持循环，直到我们到达列表的末尾
//        （这意味着只要当前索引位置小于列表的长度，就保持循环）
//         */
//        while (index < words.size()) {
//
//            // 创建一个新的TextView
//            TextView wordView = new TextView(this);
//
//            // 将文本设置为当前索引处的字
//            wordView.setText(words.get(index));
//
//            // 将此TextView作为另一个子项添加到此布局的根视图
//            rootView.addView(wordView);
//
//            // 将索引变量增加1
//            index++;
//        }


//        //创建TextView对象
//        //该对象会存储在叫做WordView的变量中 数据类型是textView
//        TextView wordView = new TextView(this);
//        /*
//        为了更改在屏幕上显示的文本，我对wordView对象调用serText方法
//        并传入一个输入参数，即要显示的文本
//        在这里我希望TextView显示单词列表中第0个索引处存储的字符串
//         */
//        wordView.setText(words.get(0));
//        /*
//        将wordView视图添加到父视图rootView的子视图
//         */
//        rootView.addView(wordView);


    }

    @Override
    protected void onStop() {
        super.onStop();
        //当活动停止时，释放媒体播放器资源，
        // 因为我们不会再播放任何声音。
        releaseMediaPlayer();
    }

    /**
     * 通过释放其资源来清理媒体播放器。
     */
    private void releaseMediaPlayer() {
        // 如果媒体播放器不为空，则它可能当前正在播放声音。
        if (mMediaPlayer != null) {
            // 无论媒体播放器的当前状态如何，都释放其资源，因为我们不再需要它。
            mMediaPlayer.release();

            // 将媒体播放器设置为null。 对于我们的代码，
            // 我们已经决定将媒体播放器设置为null是一种简单的方法
            // 来告诉媒体播放器目前没有配置为播放音频文件。
            mMediaPlayer = null;

            // 在媒体播放器上设置监听器，以便在声音播放完毕后，
            // 我们可以停止并释放媒体播放器。
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
