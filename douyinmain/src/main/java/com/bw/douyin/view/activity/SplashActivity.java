package com.bw.douyin.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseActivity;

/**
 *  @author 赵亮
 *  @date 2020/11/20 14:06
 *  @desc App启动闪屏页面，展示软件logo
 *  @version 1.0
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        // 设置页码要显示的布局文件
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        // 设置全屏
        setFullScreen();

        // 进入主页的延时计时器
        new CountDownTimer(500, 500) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                // 进入主页面
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                // 销毁当前页面
                finish();
            }
        }.start();
    }
}