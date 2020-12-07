package com.bw.douyin.view.fragment.personal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.view.adapter.WorkAdapter;

import butterknife.BindView;

/**
 *  @auther Dell Precision
 *  @date 2020/11/29 16:02
 *  @desc 作品Fragment
 *  @version 1.0
 */
public class WorkFragment extends BaseFragment {

    @BindView(R.id.rv_work_list)
    RecyclerView recyclerView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new WorkAdapter());
    }
}