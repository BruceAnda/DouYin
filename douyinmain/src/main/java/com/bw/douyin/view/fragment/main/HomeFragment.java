package com.bw.douyin.view.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.view.adapter.CommPagerAdapter;
import com.bw.douyin.view.fragment.home.FollowFragment;
import com.bw.douyin.view.fragment.home.RecommendFragment;
import com.bw.douyin.view.fragment.home.SameCityFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 13:31
 *  @desc 首页Fragment
 *  @version 1.0
 */
public class HomeFragment extends BaseFragment {

    /**
     * 首页标题tab
     */
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    private String[] tabTitleText = {"同城", "关注", "推荐"};

    /**
     * 首页ViewPager
     */
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private SameCityFragment sameCityFragment;
    private FollowFragment followFragment;
    private RecommendFragment recommendFragment;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        initPager();

        initTitleTab();
    }

    /**
     * 初始化页面
     */
    private void initPager() {
        sameCityFragment = new SameCityFragment();
        followFragment = new FollowFragment();
        recommendFragment = new RecommendFragment();
        fragments.add(sameCityFragment);
        fragments.add(followFragment);
        fragments.add(recommendFragment);

        mVpContent.setAdapter(new CommPagerAdapter(getChildFragmentManager(), fragments, tabTitleText));
    }

    /**
     * 初始化标题tab
     */
    private void initTitleTab() {
        mTabTitle.setupWithViewPager(mVpContent);

        for (int i = 0; i < tabTitleText.length; i++) {
            // 添加底部tab
            View tabView = View.inflate(getActivity(), R.layout.main_bottom_tab_item, null);
            setTabText(tabTitleText[i], tabView, 15, R.color.tab_normal);
            // 设置第一个tab文本为白色
            if (i == 0) {
                setTabText(tabTitleText[0], tabView, 16, R.color.white);
            }
            mTabTitle.getTabAt(i).setCustomView(tabView);
        }

        addTitleTabListener();

        // 选中推荐
        mTabTitle.getTabAt(2).select();
    }

    /**
     * 底部Bottom添加监听
     */
    private void addTitleTabListener() {
        // 添加底部tab监听
        mTabTitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab选中回调
                View tabView = tab.getCustomView();
                setTabText(tabTitleText[tab.getPosition()], tabView, 16, R.color.white);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tab不选中回调
                // tab选中回调
                View tabView = tab.getCustomView();
                setTabText(tabTitleText[tab.getPosition()], tabView, 15, R.color.tab_normal);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 设置tab文本的方法
     * @param text
     * @param tabView
     * @param tabTextSize
     * @param textColor
     */
    private void setTabText(String text, View tabView, int tabTextSize, int textColor) {
        TextView tabText = tabView.findViewById(R.id.tab_text);
        // 设置文字大小
        float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, tabTextSize, getResources().getDisplayMetrics());
        tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP, selectedSize);
        tabText.setTextColor(getResources().getColor(textColor));
        tabText.setText(text);
    }
}