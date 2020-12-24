package com.tarena.douyin.view.fragment.personal;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tarena.douyin.R;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.view.adapter.WorkAdapter;

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