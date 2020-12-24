package com.tarena.douyin.app;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.viewpager2.widget.ViewPager2;

import com.danikula.videocache.HttpProxyCacheServer;
import com.meishe.sdkdemo.MSApplication;

/**
 *  @auther Dell Precision
 *  @date 2020/12/19 13:22
 *  @desc 全局Application
 *  @version 1.0
 */
public class DouYinApp extends MSApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    //视频缓存代理服务
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        DouYinApp app = (DouYinApp) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)
                .maxCacheFilesCount(30)
                .build();
    }
}
