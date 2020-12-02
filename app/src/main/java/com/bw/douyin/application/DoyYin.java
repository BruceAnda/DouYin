package com.bw.douyin.application;

import android.app.Application;
import android.content.Context;

import androidx.annotation.FontRes;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.cache.ProxyCacheManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import java.io.File;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 *  @author 赵亮
 *  @date 2020/11/23 18:06
 *  @desc 全局Application
 *  @version 1.0
 */
public class DoyYin extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* 初始化Fresco */
        Fresco.initialize(this);
    }
}
