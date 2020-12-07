package com.bw.douyin.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.view.adapter.CommPagerAdapter;
import com.bw.douyin.view.adapter.PersonalViewPagerAdapter;
import com.bw.douyin.view.fragment.personal.DynamicFragment;
import com.bw.douyin.view.fragment.personal.LikeFragment;
import com.bw.douyin.view.fragment.personal.WorkFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  @author 赵亮
 *  @date 2020/11/21 10:33
 *  @desc 个人主页Fragment
 *  @version 1.0
 */
public class PersonalHomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    private String[] titles = {"作品144", "动态189", "喜欢111"};
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private WorkFragment workFragment;
    private DynamicFragment dynamicFragment;
    private LikeFragment likeFragment;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal_home;
    }

    @Override
    protected void init() {
        initPager();

        initTab();
    }

    /**
     * 初始化ViewPager
     */
    private void initPager() {
        workFragment = new WorkFragment();
        dynamicFragment = new DynamicFragment();
        likeFragment = new LikeFragment();

        fragments.add(workFragment);
        fragments.add(new WorkFragment());
        fragments.add(new WorkFragment());

        viewPager.setAdapter(new CommPagerAdapter(getChildFragmentManager(), fragments, titles));
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
       tabLayout.setupWithViewPager(viewPager);
    }


}