package com.bw.douyin.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  @author 赵亮
 *  @date 2020/11/21 10:25
 *  @desc 基类Fragment
 *  @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 页面显示布局的引用,供子类使用
     */
    protected View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 填充布局
        rootView = inflater.inflate(setLayoutId(), container, false);
        // ButterKnife绑定
        unbinder = ButterKnife.bind(this, rootView);
        // 调用初始化的方法
        init();
        // 返回页面显示view
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 加载数据的方法，子类需要加载数据重写，不需要加载不重写
     */
    protected void initData() {

    }

    /**
     * 设置布局的方法
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化
     */
    protected abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
