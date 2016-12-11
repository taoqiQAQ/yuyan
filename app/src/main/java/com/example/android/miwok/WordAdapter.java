package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mac on 2016/12/11.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    public WordAdapter(Activity context, ArrayList<Word> words) {
        // 这里，我们为context和列表初始化ArrayAdapter的内部存储。
        // 当ArrayAdapter填充单个TextView时使用第二个参数。
        // 因为这是一个用于两个TextView和一个ImageView的自定义适配器，所以适配器不是
        // 要使用这个第二个参数，因此它可以是任何值。 这里，我们使用0。
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 检查现有视图是否正在重用，否则扩充视图
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // 获取位于列表中此位置的{@link Word}对象
        Word currentWord = getItem(position);

        // 在ID version_name的list_item.xml布局中查找TextView
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // 从当前的AndroidFlavor对象和获取版本名称
        //在TextView上设置此文本
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // 在ID version_number的list_item.xml布局中查找TextView
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // 从当前的AndroidFlavor对象和获取版本号
        // 将此文本设置为数字TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());


        // 在list_item.xml布局中使用ID list_item_icon查找ImageView
        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.icon_image_view);

        if (currentWord.hadImage()) {
            // 从当前的AndroidFlavor对象获取图像资源ID，并将图像设置为iconView
            iconImageView.setImageResource(currentWord.getImageResourceId());
            // 请确保视图是可见的
            iconImageView.setVisibility(View.VISIBLE);
        }
        else {
            //否则隐藏iconImageView
            iconImageView.setVisibility(View.GONE);
        }
        // 返回整个列表项布局（包含2个TextView和一个ImageView），以便它可以显示在ListView中
        return listItemView;
    }
}
