package com.example.android.miwok;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//{@link CategoryAdapter} is a {@link FragmentPagerAdapter} that
//可以基于作为{@link Word}对象的列表的数据源来为每个列表项目提供布局。

public class CategoryAdapter extends FragmentPagerAdapter{

    //应用程序的上下文
    private Context mContext;

    //创建一个新的{@link CategoryAdapter}对象。
    // @param context 是应用程序的上下文
    // @param fm是片段管理器，它将跨每个片段保持适配器中的每个片段的状态。
    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }



//返回给定页码应显示的{@link Fragment}。
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else {
            return new PhrasesFragment();
        }
    }
//返回总页数。
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}

