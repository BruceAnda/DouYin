package com.tarena.douyin.view.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.danikula.videocache.HttpProxyCacheServer;
import com.tarena.douyin.model.bean.VideoBean;
import com.tarena.douyin.view.fragment.home.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 20:05
 *  @desc 推荐视频适配器
 *  @version 1.0
 */
public class RecommendVideoAdapter extends FragmentStateAdapter {

    private List<VideoBean> urls = new ArrayList<>();

    public void setUrls(List<VideoBean> urls, boolean isRefresh) {
        if (isRefresh) {
            urls.clear();
        }
        this.urls.addAll(urls);
    }

    public RecommendVideoAdapter(@NonNull Fragment fragment, List<VideoBean> urls) {
        super(fragment);
        this.urls = urls;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putSerializable("url", urls.get(position));
        videoFragment.setArguments(args);
        return videoFragment;
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
