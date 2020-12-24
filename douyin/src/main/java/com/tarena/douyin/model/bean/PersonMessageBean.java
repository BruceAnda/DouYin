package com.tarena.douyin.model.bean;

/**
 *  @auther Dell Precision
 *  @date 2020/12/2 19:51
 *  @desc 关注Bean
 *  @version 1.0
 */
public class PersonMessageBean {

    private String userId;
    private String avatar;
    private String nikeName;
    private String sex;
    private int age;
    private String location;
    private String followMessage;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFollowMessage() {
        return followMessage;
    }

    public void setFollowMessage(String followMessage) {
        this.followMessage = followMessage;
    }
}
