package com.tarena.douyin.view.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.danikula.videocache.HttpProxyCacheServer;
import com.tarena.douyin.R;
import com.tarena.douyin.app.DouYinApp;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.event.FollowVideoEvent;
import com.tarena.douyin.model.bean.VideoBean;
import com.tarena.douyin.view.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 22:01
 *  @desc 视频播放Fragment
 *  @version 1.0
 */
public class FollowVideoFragment extends BaseFragment {
    public final String TAG = FollowVideoFragment.class.getName();

    //视频缓存代理服务
    private HttpProxyCacheServer proxy;

    @BindView(R.id.video_player)
    VideoView videoPlayer;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init() {

        proxy = DouYinApp.getProxy(getActivity());

        Bundle arguments = getArguments();
        VideoBean videoBean = (VideoBean) arguments.getSerializable("url");
        //缩略图
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        Glide.with(getActivity()).load(videoBean.getCoverRes()).apply(options).into(videoPlayer.posterImageView);

        setPlay(videoBean);
    }

    private void setPlay(VideoBean videoBean) {
        //不保存播放进度
        Jzvd.SAVE_PROGRESS = false;
        //取消播放时在非WIFIDialog提示
        Jzvd.WIFI_TIP_DIALOG_SHOWED = true;
        // 清除某个URL进度
        //JZUtils.clearSavedProgress(activity, path)
        videoPlayer.setUp(proxy.getProxyUrl(videoBean.getVideoRes()), "", JzvdStd.SCREEN_FULLSCREEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.startVideoAfterPreloading();

        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        JzvdStd.goOnPlayOnPause();
    }
}