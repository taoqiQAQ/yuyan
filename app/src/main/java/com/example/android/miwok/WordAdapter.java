package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
//{@link WordAdapter}是一个{@link ArrayAdapter}，可以为每个列表项提供布局
//基于数据源，它是{@link Word}对象的列表。
public class WordAdapter extends ArrayAdapter<Word> {
    //此列表的背景颜色的资源ID
    private int mColorResourceId;

    /**
     * 创建一个新的{@link WordAdapter}对象。
     *
     * @param context 是创建适配器的当前上下文（即Activity）。
     * @param words   是要显示的{@link Word}的列表。
     * @param colorResourceId 是此单词列表的背景颜色的资源ID。
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // 这里，我们为context和列表初始化ArrayAdapter的内部存储。
        // 当ArrayAdapter填充单个TextView时使用第二个参数。
        // 因为这是一个用于两个TextView和一个ImageView的自定义适配器，所以适配器不是
        // 要使用这个第二个参数，因此它可以是任何值。 这里，我们使用0。
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 检查现有视图是否正在重用，否则扩充视图
        View listItemView = convertView;
        if (listItemView == null) {
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
        } else {
            //否则隐藏iconImageView
            iconImageView.setVisibility(View.GONE);
        }

        // 设置列表项的主题颜色
        View textContainer = listItemView.findViewById(R.id.text_container);
        // 查找资源ID映射到的颜色
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // 设置文本容器视图的背景颜色
        textContainer.setBackgroundColor(color);

        // 返回整个列表项布局（包含2个TextView和一个ImageView），以便它可以显示在ListView中
        return listItemView;
    }
}
