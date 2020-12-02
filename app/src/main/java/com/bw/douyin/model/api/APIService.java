package com.bw.douyin.model.api;

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
        List<SameCityBean> list = generateDemoData();

        if (callback != null) {
            callback.onSuccess(list);
        }
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
    private List<SameCityBean> generateDemoData() {
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
