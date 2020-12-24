package com.tarena.douyin.base;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  @author 赵亮
 *  @date 2020/11/20 13:48
 *  @desc 基类Activity
 *  @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * ButterKnife绑定对象
     */
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayoutId());

        // ButterKnife绑定
        unbinder = ButterKnife.bind(this);

        init();
    }

    /**
     * 设置显示的布局
     * @return  在Activity中要显示的布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化操作
     */
    protected abstract void init();

    /**
     * 设置状态栏颜色
     * @param color
     */
    protected void setStatusBarColor(int color) {
        ImmersionBar.with(this).statusBarColor(color);
    }

    /**
     * 去除状态栏
     */
    protected void hideStatusBar() {
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
    }

    /**
     * 设置全屏
     */
    protected void setFullScreen() {
        ImmersionBar.with(this).init();
    }

    /**
     * 保持不息屏
     */
    protected void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Activity退出动画
     * @param animId
     */
    protected void setExitAnimation(int animId) {
        // 第一个参数为进入动画，第二个参数为退出动画
        overridePendingTransition(0, animId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // ButterKnife解绑
        unbinder.unbind();
    }
}
