package com.bw.douyin.event;

/**
 *  @author 赵亮
 *  @date 2020/11/23 18:39
 *  @desc 暂停视频播放的Event
 *  @version 1.0
 */
public class PauseVideoEvent {

    private boolean playOrPause;

    public PauseVideoEvent(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
