package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    //处理所有声音文件的播放
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // 创建单词列表
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        // 创建一个{@link WordAdapter}，其数据源是{@link Word}的列表。
        // 适配器知道如何为列表中的每个项目创建列表项。
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        // 在{@link Activity}的视图层次结构中查找{@link ListView}对象。
        // 应该有一个{@link ListView}，
        // 视图ID称为list，它在word_list.xml布局文件中声明。
        ListView listView = (ListView) findViewById(R.id.list);

        // 让{@link ListView}使用我们在上面创建的{@link WordAdapter}，
        // 以便{@link ListView}会在列表中显示每个{@link Word}的列表项。
        listView.setAdapter(adapter);

        //设置点击监听器以在单击列表项时播放音频
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {

                //在用户点击的给定位置获取{@link Word}对象
                Word word = words.get(position);
                //为与当前字相关联的音频资源创建并设置{@link Media Player}
                mMediaPlayer  = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
                //启动音频文件
                mMediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        });
    }
}