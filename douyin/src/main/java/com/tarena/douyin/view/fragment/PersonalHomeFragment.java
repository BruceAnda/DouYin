package com.tarena.douyin.view.fragment;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.tarena.douyin.R;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.view.adapter.CommPagerAdapter;
import com.tarena.douyin.view.fragment.personal.DynamicFragment;
import com.tarena.douyin.view.fragment.personal.LikeFragment;
import com.tarena.douyin.view.fragment.personal.WorkFragment;
import com.tarena.douyin.view.widget.ScaleScrollView;
import com.tarena.douyin.view.widget.TitleLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  @author 赵亮
 *  @date 2020/11/21 10:33
 *  @desc 个人主页Fragment
 *  @version 1.0
 */
public class PersonalHomeFragment extends BaseFragment implements ScaleScrollView.OnScrollChangeListener{

    private TabLayout tab1, tab2;
    private int colorPrimary;
    private ArgbEvaluator evaluator;



    private int getStatusBarHeight() {
        int height = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = getResources().getDimensionPixelSize(resId);
        }
        return height;
    }

    @BindView(R.id.banner)
    AppCompatImageView banner;
    @BindView(R.id.scrollView)
    ScaleScrollView scrollView;
    @BindView(R.id.statusView)
    View statusView;

    @BindView(R.id.title_layout)
    TitleLayout titleLayout;

    @BindView(R.id.tab1)
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
        return R.layout.fragment_personal2;
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
       /* LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusView.setLayoutParams(lp);
        statusView.setBackgroundColor(Color.TRANSPARENT);
        statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);*/

        scrollView.setTargetView(banner);
        scrollView.setOnScrollChangeListener(this);

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

    @Override
    public void onScrollChange(NestedScrollView v, int x, int y, int ox, int oy) {
        if (null != tab1 && null != tab2 && null != titleLayout && null != statusView) {
            int distance = tab1.getTop() - titleLayout.getHeight() - statusView.getHeight();
            float ratio = v.getScaleY() * 1f / distance;
            if (distance <= v.getScrollY()) {
                ratio = 1;
                if (tab2.getVisibility() != View.VISIBLE) {
                    tab2.setVisibility(View.VISIBLE);
                    statusView.setBackgroundColor(colorPrimary);
                }
            } else {
                if (tab2.getVisibility() == View.VISIBLE) {
                    tab2.setVisibility(View.INVISIBLE);
                    statusView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            if (null == evaluator) {
                evaluator = new ArgbEvaluator();
            }
            titleLayout.setBackgroundColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, colorPrimary));
            titleLayout.setTitleColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, Color.WHITE));
        }
    }

}