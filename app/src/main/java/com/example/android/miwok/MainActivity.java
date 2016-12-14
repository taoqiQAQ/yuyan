/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 将活动的内容设置为使用activity_main.xml布局文件
        setContentView(R.layout.activity_main);

        // 找到将允许用户在片段之间滑动的视图分页器
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // 创建一个适配器，它知道应该在每个页面上显示哪个片段
        CategoryAdapter adapter = new CategoryAdapter(this,getSupportFragmentManager());

        // 将适配器设置为viewpager
        viewPager.setAdapter(adapter);

        // 查找显示选项卡的选项卡布局
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // 使用view pager连接制表符布局。 这会
        // 1.刷新视图分页器时更新选项卡布局
        // 2.选择选项卡时更新视图寻呼机
        // 3.使用view pager的适配器标题设置选项卡布局的选项卡名称
        //通过调用onPageTitle（）
        tabLayout.setupWithViewPager(viewPager);
    }
}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // 将活动的内容设置为使用activity_main.xml布局文件
//        setContentView(R.layout.activity_main);
//
//        //第34 38 行跟NumbersClickListener一起注销的 要一起用 跳出提示框
//       /* NumbersClickListener clickListner = new NumbersClickListener();*/
//        //找到显示number类别的视图
//        TextView numbers = (TextView) findViewById(R.id.numbers);
//        //将ClickListener对象传入值
//        /*numbers.setOnClickListener(clickListner);*/
//
//        // 在该视图上设置一个点击监听器
//        numbers.setOnClickListener(new View.OnClickListener() {
//            //当点击数字View时，将执行此方法中的代码。
//            @Override
//            public void onClick(View view) {
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//                startActivity(numbersIntent);
//            }
//        });
//
//
//        //找到显示family类的视图
//        TextView family = (TextView) findViewById(R.id.family);
//        //在family视图上设置一个点击监听器
//        family.setOnClickListener(new View.OnClickListener() {
//                                      //当点击family类时，将执行此方法中的代码。
//                                      @Override
//                                      //创建一个新意图打开{@link FamilyActivity}
//                                      public void onClick(View view) {
//                                          Intent famliyIntent = new Intent(MainActivity.this, FamilyActivity.class);
//                                          //启动一个新的Activity
//                                          startActivity(famliyIntent);
//                                      }
//                                  }
//
//        );
//        //找到显示color类的视图
//        TextView colors = (TextView) findViewById(R.id.colors);
//        //在color视图上设置一个点击监听器
//        colors.setOnClickListener(new View.OnClickListener() {
//            //当点击color类时，将执行此方法的代码
//            @Override
//            //创建一个新的意图打开{@link ColorsActivity}
//            public void onClick(View view) {
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//                //启动一个新的Activity
//                startActivity(colorsIntent);
//            }
//        });
//
//        //找到显示phrases类的视图
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//        //在phrases视图上设置一个点击监听器
//        phrases.setOnClickListener(new View.OnClickListener() {
//            //当点击phrases类时，将执行此方法的代码
//            @Override
//            //创建一个新的意图打开{@link PhrasesActivity}
//            public void onClick(View view) {
//                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
//                //启动一个新的Activity
//                startActivity(phrasesIntent);
//            }
//        });
//
//    }
//
//
//}
