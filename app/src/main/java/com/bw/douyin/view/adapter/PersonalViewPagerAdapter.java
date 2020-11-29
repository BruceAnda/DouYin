package com.bw.douyin.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 *  @auther Dell Precision
 *  @date 2020/11/29 16:03
 *  @desc 个人中心 作品 动态 喜欢 适配器
 *  @version 1.0
 */
public class PersonalViewPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments;

    public PersonalViewPagerAdapter(@NonNull Fragment fragment, List<Fragment> fragments) {
        super(fragment);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
