package com.bw.douyin.view.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 *  @auther Dell Precision
 *  @date 2020/11/29 15:14
 *  @desc 自定义视频播放器
 *  @version 1.0
 */
public class VideoView extends StandardGSYVideoPlayer {
    public VideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        GSYVideoType.setScreenScaleRatio(GSYVideoType.SCREEN_MATCH_FULL);
    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        setViewShowState(mLoadingProgressBar, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        super.changeUiToPlayingBufferingShow();
        setViewShowState(mLoadingProgressBar, INVISIBLE);
        setViewShowState(mBottomContainer, VISIBLE);
    }

    @Override
    protected void changeUiToPlayingBufferingClear() {
        super.changeUiToPlayingBufferingClear();
        setViewShowState(mLoadingProgressBar, INVISIBLE);
    }

    @Override
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        setViewShowState(mStartButton, INVISIBLE);
    }

    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
    }

    @Override
    protected void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
    }

    @Override
    protected void changeUiToCompleteShow() {
        super.changeUiToCompleteShow();
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mBottomContainer, VISIBLE);
    }

    @Override
    protected void changeUiToCompleteClear() {
        super.changeUiToCompleteClear();
        setViewShowState(mStartButton, INVISIBLE);
    }
}
