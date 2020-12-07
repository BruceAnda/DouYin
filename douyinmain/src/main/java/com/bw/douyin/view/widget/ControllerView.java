package com.bw.douyin.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.bw.douyin.R;

import butterknife.ButterKnife;

/**
 *  @auther Dell Precision
 *  @date 2020/11/28 21:58
 *  @desc 视频播放右侧头像 点赞 评论 分享view
 *  @version 1.0
 */
public class ControllerView extends RelativeLayout implements View.OnClickListener {

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        ButterKnife.bind(this, inflate(getContext(), R.layout.view_controller, this));
    }


    @Override
    public void onClick(View v) {

    }
}
