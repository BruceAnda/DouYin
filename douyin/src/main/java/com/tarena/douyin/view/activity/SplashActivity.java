package com.tarena.douyin.view.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.meishe.sdkdemo.base.BasePermissionActivity;
import com.meishe.sdkdemo.utils.Util;
import com.tarena.douyin.DouYinMainActivity;
import com.tarena.douyin.R;
import com.tarena.douyin.base.BaseActivity;

import java.util.List;

/**
 * @author 赵亮
 * @version 1.0
 * @date 2020/11/20 14:06
 * @desc App启动闪屏页面，展示软件logo
 */
public class SplashActivity extends BasePermissionActivity {

   /* protected int setLayoutId() {
        // 设置页码要显示的布局文件
        return R.layout.activity_splash;
    }*/

    @Override
    protected int initRootView() {
        ImmersionBar.with(this).init();
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return R.layout.activity_splash;
        }
        return R.layout.activity_splash;
    }

    protected void init() {
        // 设置全屏

        // 进入主页的延时计时器
        new CountDownTimer(500, 500) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                // 进入主页面
                startActivity(new Intent(SplashActivity.this, DouYinMainActivity.class));
                // 销毁当前页面
                finish();
            }
        }.start();
    }

    @Override
    protected List<String> initPermissions() {
        return Util.getAllPermissionsList();
    }

    @Override
    protected void hasPermission() {
        init();
    }

    @Override
    protected void nonePermission() {
        finish();
    }

    @Override
    protected void noPromptPermission() {
        finish();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        checkPermissions();
        if (hasAllPermission()) {
            init();
        }
    }

    @Override
    public void onClick(View view) {

    }
}