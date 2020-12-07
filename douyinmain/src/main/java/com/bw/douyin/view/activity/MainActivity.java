package com.bw.douyin.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseActivity;
import com.bw.douyin.event.PauseVideoEvent;
import com.bw.douyin.view.adapter.CommPagerAdapter;
import com.bw.douyin.view.fragment.MainFragment;
import com.bw.douyin.view.fragment.PersonalHomeFragment;
import com.cgfay.filter.glfilter.resource.FilterHelper;
import com.cgfay.filter.glfilter.resource.MakeupHelper;
import com.cgfay.filter.glfilter.resource.ResourceHelper;
import com.cgfay.uitls.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 赵亮
 * @version 1.0
 * @date 2020/11/21 8:30
 * @desc 抖音主页面
 */
public class MainActivity extends BaseActivity {

    /**
     * 装页面的ViewPager
     */
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    /**
     * 存放Fragment对象的容器
     */
    private List<Fragment> fragments = new ArrayList<>();

    /**
     * 主页面Fragment对象
     */
    private MainFragment mainFragment = new MainFragment();

    /**
     * 个人中心页面Fragment
     */
    private PersonalHomeFragment personalHomeFragment = new PersonalHomeFragment();

    /**
     * 主页面适配器
     */
    private CommPagerAdapter pagerAdapter;

    /**
     * 上次按返回键的时间
     */
    private long lastTime;

    /**
     * 连续按返回键退出时间
     */
    private final int EXIT_TIME = 2000;



    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        // 向容器中存放 主页面
        fragments.add(mainFragment);
        // 向容器中添加 个人页面
        fragments.add(personalHomeFragment);
        // 创建适配器对象
        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, null);
        // 设置适配器
        viewpager.setAdapter(pagerAdapter);
        checkPermissions();
        if (PermissionUtils.permissionChecking(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            initResources();
        }
    }

    private static final int REQUEST_CODE = 0;
    private void checkPermissions() {
        PermissionUtils.requestPermissions(this, new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initResources();
            }
        }
    }

    /**
     * 初始化动态贴纸、滤镜等资源
     */
    private void initResources() {
        new Thread(() -> {
            ResourceHelper.initAssetsResource(MainActivity.this);
            FilterHelper.initAssetsFilter(MainActivity.this);
            MakeupHelper.initAssetsMakeup(MainActivity.this);
        }).start();
    }

}