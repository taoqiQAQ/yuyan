package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

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
        ArrayList<String> words = new ArrayList<String>();
        //这是元素应如何添加到数组列表
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
//        //通过将每个元素打印到日志中来验证列表的内容
//        Log.v("NumbersActivity", "word at index 0: " + words.get(0));
//        Log.v("NumbersActivity", "word at index 1: " + words.get(1));
//        Log.v("NumbersActivity", "word at index 2: " + words.get(2));


        //找到rootView的LinearLayout
        //将其储存在叫rootView的变量中
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

      //用for循环
        for (int index = 0; index < words.size(); index++) {

            //创建一个新的TextView
            TextView wordView = new TextView(this);

            // 将文本设置为当前索引处的字
            wordView.setText(words.get(index));

            // 将此TextView作为另一个子项添加到此布局的根视图
            rootView.addView(wordView);
        }

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
}
