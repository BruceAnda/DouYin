package com.tarena.douyin.model.bean;

/**
 *  @auther Dell Precision
 *  @date 2020/12/2 14:37
 *  @desc 同城列表Bean
 *  @version 1.0
 */
public class SameCityBean {

    /**
     * 同城推荐ID
     */
    private String id;

    /**
     * 同城列表图片
     */
    private String imagePic;

    /**
     * 同城头像
     */
    private String avatar;

    /**
     * 同城距离
     */
    private String distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePic() {
        return imagePic;
    }

    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
