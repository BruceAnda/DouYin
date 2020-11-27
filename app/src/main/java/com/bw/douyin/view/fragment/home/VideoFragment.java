package com.bw.douyin.view.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 22:01
 *  @desc 视频播放Fragment
 *  @version 1.0
 */
public class VideoFragment extends BaseFragment {

    @BindView(R.id.video_player)
    StandardGSYVideoPlayer videoPlayer;

    private long mCurrentPosition = 0L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCurrentPosition = 0;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init() {
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getFullscreenButton().setVisibility(View.GONE);
        videoPlayer.setNeedShowWifiTip(false);
        videoPlayer.setLooping(true);
        videoPlayer.setDismissControlTime(0);

        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        videoPlayer.setUpLazy(url, false, null, null, "");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCurrentPosition > 0) {
            videoPlayer.onVideoResume(false);
        } else {
            videoPlayer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    videoPlayer.startPlayLogic();
                }
            }, 200);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }
}