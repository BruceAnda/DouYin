package com.bw.douyin.view.fragment;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.FragmentUtils;
import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.view.fragment.main.FriendFragment;
import com.bw.douyin.view.fragment.main.HomeFragment;
import com.bw.douyin.view.fragment.main.MessageFragment;
import com.bw.douyin.view.fragment.main.MyFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * @author 赵亮
 * @version 1.0
 * @date 2020/11/21 10:32
 * @desc 主页面Fragment
 * 包含
 * 首页：HomeFragment
 * 朋友：FriendFragment
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_bottom)
    TabLayout mBottomTab;
    private String[] mBottomTabTitle = {"首页", "朋友", "", "消息", "我"};

    private HomeFragment homeFragment;
    private FriendFragment friendFragment;
    private MessageFragment messageFragment;
    private MyFragment myFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {

        initBottomTab();

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        mCurrentShowFragment = homeFragment;
        FragmentUtils.add(getChildFragmentManager(), homeFragment, R.id.container);
    }

    /**
     * 初始化底部导航
     */
    private void initBottomTab() {
        // 添加底部tab
        for (int i = 0; i < mBottomTabTitle.length; i++) {
            View tabView = View.inflate(getActivity(), R.layout.main_bottom_tab_item, null);
            setTabText(mBottomTabTitle[i], tabView, 13, R.color.tab_normal);
            // 设置第一个tab文本为白色
            if (i == 0) {
                setTabText(mBottomTabTitle[0], tabView, 14, R.color.white);
            }
            // 添加Tab
            mBottomTab.addTab(mBottomTab.newTab().setCustomView(tabView));
        }
        addBottomTabListener();
    }

    /**
     * 底部Bottom添加监听
     */
    private void addBottomTabListener() {
        // 添加底部tab监听
        mBottomTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab选中回调
                View tabView = tab.getCustomView();
                setTabText(mBottomTabTitle[tab.getPosition()], tabView, 14, R.color.white);
                switch (tab.getPosition()) {
                    case 0:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        changeFragment(homeFragment);
                        break;
                    case 1:
                        if (friendFragment == null) {
                            friendFragment = new FriendFragment();
                        }
                        changeFragment(friendFragment);
                        break;
                    case 2: // 中间+按钮选中

                        break;
                    case 3:
                        if (messageFragment == null) {
                            messageFragment = new MessageFragment();
                        }
                        changeFragment(messageFragment);
                        break;
                    case 4:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                        }
                        changeFragment(myFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tab不选中回调
                // tab选中回调
                View tabView = tab.getCustomView();
                setTabText(mBottomTabTitle[tab.getPosition()], tabView, 13, R.color.tab_normal);
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

    //当前显示的fragment
    private Fragment mCurrentShowFragment;
    /** 修改显示的内容 不会重新加载 **/
    public void changeFragment(Fragment to) {
        if (mCurrentShowFragment != to) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mCurrentShowFragment).add(R.id.container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mCurrentShowFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mCurrentShowFragment = to;
        }
    }
}