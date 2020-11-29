package com.bw.douyin.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *  @auther Dell Precision
 *  @date 2020/11/28 22:15
 *  @desc 跑马灯TextView
 *  @version 1.0
 */
public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused(){
        return true;
    }

}
