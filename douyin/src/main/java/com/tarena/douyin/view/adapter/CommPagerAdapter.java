package com.tarena.douyin.view.adapter;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 *  @author 赵亮
 *  @date 2020/11/21 11:00
 *  @desc 公共的ViewPager适配器，多个页面滑动使用
 *  @version 1.0
 */
public class CommPagerAdapter extends FragmentStatePagerAdapter {

    /**
     * 装Fragment的容器
     */
    private List<? extends Fragment> mItems;

    /**
     * 装Title的容器
     */
    private String[] mTitles;

    /**
     * 构造方法
     * @param fm
     * @param items
     * @param titles
     */
    public CommPagerAdapter(@NonNull FragmentManager fm, List<? extends Fragment> items, String[] titles) {
        super(fm);
        this.mItems = items;
        this.mTitles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mItems.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // 如果没有设置title或title的数量小于页面数量调用父类的方法，避免程序崩溃或抛出空指针异常
        if (mTitles == null || mTitles.length < mItems.size()) {
            return super.getPageTitle(position);
        }
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // 刷新页面的时候强制重绘item
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        // 不保存状态
        return null;
    }
}
