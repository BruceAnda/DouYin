package com.bw.douyin.view.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.view.adapter.RecommendVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 13:26
 *  @desc 推荐页面
 *  @version 1.0
 */
public class RecommendFragment extends BaseFragment {

    @BindView(R.id.vp_video)
    ViewPager2 videoViewPager;

    List<String> urls = new ArrayList<>();
    private RecommendVideoAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        addUrls();

        adapter = new RecommendVideoAdapter(this, urls);
        adapter.setUrls(urls, false);
        videoViewPager.setAdapter(adapter);

        videoViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == urls.size() - 5) {
                    addUrls();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void addUrls() {
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video1.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video2.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video3.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video4.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video5.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video6.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video7.mp4");
        urls.add("http://blog.zhaoliang5156.cn/douyin/video/video8.mp4");
    }
}