package com.meishe.sdkdemo.mimodemo.base;

import androidx.fragment.app.FragmentManager;

import com.meicam.sdk.NvsTimeline;
import com.meishe.sdkdemo.R;
import com.meishe.sdkdemo.base.BaseActivity;
import com.meishe.sdkdemo.mimodemo.VideoFragment;
import com.meishe.sdkdemo.mimodemo.common.Constants;

public abstract class BaseEditActivity extends BaseActivity {
    protected VideoFragment mVideoFragment;
    protected NvsTimeline mTimeline;

    @Override
    protected void initData() {
        mTimeline = initTimeLine();
        initVideoFragment();
        initEditData();
    }

    protected abstract void initEditData();

    protected abstract NvsTimeline initTimeLine();

    protected void initVideoFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mVideoFragment = VideoFragment.newInstance(getVideoDuration());
        mVideoFragment.setEditMode(Constants.EDIT_MODE_COMPOUND_CAPTION);//设置字幕组合模式
        mVideoFragment.setTimeLine(mTimeline);
        fragmentManager.beginTransaction().add(R.id.videoLayout, mVideoFragment).commit();
        fragmentManager.beginTransaction().show(mVideoFragment);
    }

    protected abstract long getVideoDuration();

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoFragment != null) {
            mVideoFragment.stopEngine();
        }
    }

}
