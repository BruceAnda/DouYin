package com.tarena.douyin.event;

/**
 *  @author 赵亮
 *  @date 2020/11/23 18:39
 *  @desc 暂停视频播放的Event
 *  @version 1.0
 */
public class RecommendVideoEvent {

    private boolean playOrPause;

    public RecommendVideoEvent(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
