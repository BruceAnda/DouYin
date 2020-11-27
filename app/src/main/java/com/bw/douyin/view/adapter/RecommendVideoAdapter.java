package com.bw.douyin.view.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bw.douyin.R;
import com.bw.douyin.view.fragment.home.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 20:05
 *  @desc 推荐视频适配器
 *  @version 1.0
 */
public class RecommendVideoAdapter extends FragmentStateAdapter {

    private List<String> urls = new ArrayList<>();

    public void setUrls(List<String> urls, boolean isRefresh) {
        if (isRefresh) {
            urls.clear();
        }
        this.urls.addAll(urls);
    }

    public RecommendVideoAdapter(@NonNull Fragment fragment, List<String> urls) {
        super(fragment);
        this.urls = urls;
    }

    public RecommendVideoAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString("url", urls.get(position));
        videoFragment.setArguments(args);
        return videoFragment;
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
