package com.tarena.douyin.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarena.douyin.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


/**
 *  @auther Dell Precision
 *  @date 2020/11/29 15:14
 *  @desc 自定义视频播放器
 *  @version 1.0
 */
public class VideoView extends JzvdStd {
    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        bottomContainer.setVisibility(GONE);
        currentTimeTextView.setVisibility(GONE);
        totalTimeTextView.setVisibility(GONE); //当前时间
        fullscreenButton.setVisibility(GONE); //放大按钮
        topContainer.setVisibility(GONE);
        progressBar.setVisibility(GONE); //控制的
        loadingProgressBar.setVisibility(GONE); //加载loaing
        bottomProgressBar.setVisibility(GONE); //最底部的进度
        posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int posterImg, int bottomPro, int retryLayout) {
        topContainer.setVisibility(topCon);
        bottomContainer.setVisibility(bottomCon);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(GONE);
        posterImageView.setVisibility(posterImg);
        bottomProgressBar.setVisibility(GONE);
        mRetryLayout.setVerticalGravity(retryLayout);
    }

    @Override
    public void dissmissControlView() {
        if (state != Jzvd.STATE_NORMAL && state != Jzvd.STATE_ERROR && state != Jzvd.STATE_AUTO_COMPLETE) {
           post(new Runnable() {
               @Override
               public void run() {
                   bottomContainer.setVisibility(View.INVISIBLE);
                   topContainer.setVisibility(View.INVISIBLE);
                   startButton.setVisibility(View.INVISIBLE);
                   if (clarityPopWindow != null) {
                       clarityPopWindow.dismiss();
                   }
                   if (screen != Jzvd.SCREEN_TINY) {
                       bottomProgressBar.setVisibility(GONE);
                   }
               }
           });
        }
    }

    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        Log.i(Jzvd.TAG, "click blank");
        startButton.performClick();
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        Log.e("onStatePreparing", " 准备");
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        Log.e("onStatePlaying", String.valueOf(getDuration()));
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        Log.e("onStatePause:", "暂停");
    }

    @Override
    public void onStateError() {
        super.onStateError();
        Log.e("onStateError:", "错误");
    }


    @Override
    public void onCompletion() {
        super.onCompletion();
        startVideo();

    }

    @Override
    public void updateStartImage() {
        if (state == Jzvd.STATE_PLAYING) {
            startButton.setVisibility(View.INVISIBLE);
            startButton.setImageResource(R.drawable.tiktok_play_tiktok);
            replayTextView.setVisibility(GONE);
        } else if (state == Jzvd.STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(GONE);
        } else if (state == Jzvd.STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.tiktok_play_tiktok);
            replayTextView.setVisibility(GONE);
        } else {
            startButton.setImageResource(R.drawable.tiktok_play_tiktok);
            replayTextView.setVisibility(GONE);
        }
    }
}
