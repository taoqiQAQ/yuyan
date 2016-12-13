package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    //处理所有声音文件的播放
    private MediaPlayer mMediaPlayer;

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

        // 创建单词列表
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        // 创建一个{@link WordAdapter}，其数据源是{@link Word}的列表。
        // 适配器知道如何为列表中的每个项目创建列表项。
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        //在{@link Activity}的视图层次结构中查找{@link ListView}对象。
        //应该有一个{@link ListView}，视图ID称为list，它在word_list.xml布局文件中声明。
        ListView listView = (ListView) findViewById(R.id.list);

        //使{@link ListView}使用我们在上面创建的{@link WordAdapter}，
        // 以便{@link ListView}将显示列表中每个{@link Word}的列表项。
        listView.setAdapter(adapter);

        //设置点击监听器以在单击列表项时播放音频
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {

                //如果媒体播放器目前存在，请释放它，因为我们即将播放不同的声音文件
                releaseMediaPlayer();

                //在用户点击的给定位置获取{@link Word}对象
                Word word = words.get(position);

                //为与当前字相关联的音频资源创建并设置{@link Media Player}
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());

                //启动音频文件
                mMediaPlayer.start(); // no need to call prepare(); create() does that for you

                // 在媒体播放器上设置监听器，以便在声音播放完毕后，
                // 我们可以停止并释放媒体播放器。
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
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
        }
    }
}