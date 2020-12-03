package com.bw.douyin.model.api;

import com.bw.douyin.R;
import com.bw.douyin.model.bean.PersonMessageBean;
import com.bw.douyin.model.bean.RecmmentFriendBean;
import com.bw.douyin.model.bean.SameCityBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 *  @auther Dell Precision
 *  @date 2020/12/2 14:24
 *  @desc 接口访问服务类
 *  @version 1.0
 */
public class APIService {

    private static final class SingleHolder {
        private static final APIService _INSTANCE = new APIService();
    }

    private APIService() {
        initOkHttpClient();
    }

    private void initOkHttpClient() {

    }

    /**
     * 单例模式获取实例对象的方法
     * @return
     */
    public static APIService getInstance() {
        return SingleHolder._INSTANCE;
    }

    /**
     * 从服务器获取同城数据列表
     */
    public void getSameCityData(int page, int count, APIServiceCallback<List<SameCityBean>, String> callback) {

        // 模拟假数据
        List<SameCityBean> list = generateRecmmentVideoDemoData();

        if (callback != null) {
            callback.onSuccess(list);
        }
    }

    /**
     * 从服务器获取推荐朋友数据
     * @param page
     * @param count
     * @param callback
     */
    public void getRecmmendFriend(int page, int count, APIServiceCallback<List<RecmmentFriendBean>, String> callback) {

        // 模拟假数据
        List<RecmmentFriendBean> list = generateRecmmentFriendDemoData();

        if (callback != null) {
            callback.onSuccess(list);
        }
    }

    /**
     * 从服务器获取
     * @param page
     * @param count
     * @param callback
     */
    public void getPersonMessageList(int page, int count, APIServiceCallback<List<PersonMessageBean>, String> callback) {

        // 模拟加数据
        List<PersonMessageBean> list = generatePersonListDemoData();

        if (callback != null) {
            callback.onSuccess(list);
        }
    }

    /**
     * 模拟消息数据
     * @return
     */
    private List<PersonMessageBean> generatePersonListDemoData() {

        List<PersonMessageBean> list = new ArrayList<>();

        PersonMessageBean bean = new PersonMessageBean();
        bean.setIcon(R.drawable.ic_fans);
        bean.setNikeName("粉丝");
        bean.setFollowMessage("王瑞 关注了你");

        PersonMessageBean bean2 = new PersonMessageBean();
        bean2.setIcon(R.drawable.ic_interaction);
        bean2.setNikeName("互动消息");
        bean2.setFollowMessage("Jackpot 赞了你的评论");

        PersonMessageBean bean3 = new PersonMessageBean();
        bean3.setIcon(R.drawable.ic_assistant);
        bean3.setNikeName("抖音小助手");
        bean3.setFollowMessage("#青菜拼车1折拼 · 星期二");

        PersonMessageBean bean4 = new PersonMessageBean();
        bean4.setIcon(R.drawable.ic_system_message);
        bean4.setNikeName("系统通知");
        bean4.setFollowMessage("账号登录提醒 · 08-04");

        list.add(bean);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        return list;
    }

    /**
     * 生成推荐朋友数据
     * @return
     */
    private List<RecmmentFriendBean> generateRecmmentFriendDemoData() {
        List<RecmmentFriendBean> list = new ArrayList<>();

        RecmmentFriendBean bean = new RecmmentFriendBean();
        bean.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606966218252&di=1b1fec4ca313298fb571e4b5e26d6a3e&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20200402%2Fe57680c1bb574b2fb738c9c218f48cbc.jpeg");
        bean.setNikeName("王瑞");
        bean.setSex("男");
        bean.setLocation("北京");
        bean.setAge(10);
        bean.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean2 = new RecmmentFriendBean();
        bean2.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938189&di=ce2dcdbf4e9c53492c8a6c43d1cc524f&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180513%2F4d2cf830a0b64675a66db105d10bef19.jpeg");
        bean2.setNikeName("老娘最美");
        bean2.setSex("女");
        bean2.setLocation("北京");
        bean2.setAge(10);
        bean2.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean3 = new RecmmentFriendBean();
        bean3.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938305&di=3ca65d53115c9b8f16ede376ed971b34&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201809%2F01%2F20180901190625_wmpeq.thumb.700_0.jpeg");
        bean3.setNikeName("甜甜");
        bean3.setSex("女");
        bean3.setLocation("北京");
        bean3.setAge(10);
        bean3.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean4 = new RecmmentFriendBean();
        bean4.setAvatar("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=387639067,1599589691&fm=26&gp=0.jpg");
        bean4.setNikeName("娜娜");
        bean4.setSex("女");
        bean4.setLocation("北京");
        bean4.setAge(10);
        bean4.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean5 = new RecmmentFriendBean();
        bean5.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921965812&di=0ed588528339acc719a970bd20017eaa&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F05%2F20180605193845_kmhss.thumb.700_0.jpeg");
        bean5.setNikeName("紫云");
        bean5.setSex("男");
        bean5.setLocation("北京");
        bean5.setAge(10);
        bean5.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean6 = new RecmmentFriendBean();
        bean6.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938303&di=3bc35dacfcd7b1c807247a1aaaf1747b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201409%2F11%2F20140911211243_3rT4u.jpeg");
        bean6.setNikeName("夏美");
        bean6.setSex("女");
        bean6.setLocation("北京");
        bean6.setAge(10);
        bean6.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean7 = new RecmmentFriendBean();
        bean7.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938302&di=e15cc12a42819590edbee2c169875a54&imgtype=0&src=http%3A%2F%2Fpics2.baidu.com%2Ffeed%2Fbd315c6034a85edf81702f204ba7a225dc54752a.jpeg%3Ftoken%3D40ea367f6b2ef995eaa6f5faf705a7d4");
        bean7.setNikeName("冬梅");
        bean7.setSex("男");
        bean7.setLocation("北京");
        bean7.setAge(10);
        bean7.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean8 = new RecmmentFriendBean();
        bean8.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938301&di=5589de276d00e04ad0c775c96d4ef39e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201903%2F15%2F20190315004339_nWjPf.thumb.700_0.jpeg");
        bean8.setNikeName("秋香");
        bean8.setSex("男");
        bean8.setLocation("北京");
        bean8.setAge(10);
        bean8.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean9 = new RecmmentFriendBean();
        bean9.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938300&di=6f0045cd7bdf80d192f24a090d29e6a3&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201901%2F25%2F20190125131827_tphtb.thumb.700_0.jpeg");
        bean9.setNikeName("笑笑");
        bean9.setSex("男");
        bean9.setLocation("北京");
        bean9.setAge(10);
        bean9.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean10 = new RecmmentFriendBean();
        bean10.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938299&di=fa917518e996f88d621f309d9f8223e2&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3Da587b23df11f3a295a9dddcaac159007%2F500fd9f9d72a60590cfef2f92934349b023bba62.jpg");
        bean10.setNikeName("左眼皮跳跳");
        bean10.setSex("女");
        bean10.setLocation("北京");
        bean10.setAge(10);
        bean10.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean11 = new RecmmentFriendBean();
        bean11.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938299&di=bbdc7bb1ba71e88801af4c75960ca474&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201610%2F14%2F20161014200638_nXyLS.thumb.700_0.jpeg");
        bean11.setNikeName("右眼皮跳跳");
        bean11.setSex("男");
        bean11.setLocation("北京");
        bean11.setAge(10);
        bean11.setFollowMessage("可能认识的人");

        RecmmentFriendBean bean12 = new RecmmentFriendBean();
        bean12.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606921938297&di=dbcf844ff822348c51a4acc4302da489&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F05%2F20170805010159_vRrCL.thumb.700_0.jpeg");
        bean12.setNikeName("老戴");
        bean12.setSex("女");
        bean12.setLocation("北京");
        bean12.setAge(10);
        bean12.setFollowMessage("可能认识的人");

        list.add(bean);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        list.add(bean9);
        list.add(bean10);
        list.add(bean11);
        list.add(bean12);

        return list;
    }

    /**
     * 网络服务响应接口回调
     * @param <R>   响应结果
     * @param <E>   响应失败
     */
    public interface APIServiceCallback<R, E> {
        void onSuccess(R Response);

        void onError(E errMsg);
    }

    /**
     * 生成同城模拟数据的方法
     * @return
     */
    @NotNull
    private List<SameCityBean> generateRecmmentVideoDemoData() {
        List<SameCityBean> list = new ArrayList<>();

        SameCityBean bean = new SameCityBean();
        bean.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903422890&di=d3628a2cc26da4f313f6c90eaea85721&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201901%2F19%2F20190119231949_buetu.jpg");
        bean.setDistance("10km");

        SameCityBean bean2 = new SameCityBean();
        bean2.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean2.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903501108&di=fc35b70971826765439cc177ebab93d5&imgtype=0&src=http%3A%2F%2Fdiy.qqjay.com%2Fu%2Ffiles%2F2012%2F0217%2Fb693a3b6d232ffe861da22287c888729.jpg");
        bean2.setDistance("11km");

        SameCityBean bean3 = new SameCityBean();
        bean3.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean3.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg");
        bean3.setDistance("12km");

        SameCityBean bean4 = new SameCityBean();
        bean4.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean4.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903524703&di=23c5e8d9f1c3aa2e7cf626c3dc858d97&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D580e773405f431adbcd243317b37ac0f%2F50f2f9dde71190ef9c7f0079c71b9d16fffa60dc.jpg");
        bean4.setDistance("13km");

        SameCityBean bean5 = new SameCityBean();
        bean5.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean5.setAvatar("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2856846879,389279115&fm=26&gp=0.jpg");
        bean5.setDistance("14km");

        SameCityBean bean6 = new SameCityBean();
        bean6.setImagePic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903855075&di=3804ef09c2920a149ffd6b75cb916a03&imgtype=0&src=http%3A%2F%2Fvimg2.ws.126.net%2Fimage%2Fsnapshot%2F2017%2F9%2FS%2FV%2FVCUILJLSV.jpg");
        bean6.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606903613365&di=b8c1612965b81ee676dced06bd54fa45&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20190115%2F87868f21befc4e7f9007aa71efa79621.jpeg");
        bean6.setDistance("15km");

        list.add(bean);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        return list;
    }
}
