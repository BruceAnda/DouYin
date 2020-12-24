package com.tarena.douyin.view.fragment.home;

import androidx.viewpager2.widget.ViewPager2;

import com.danikula.videocache.HttpProxyCacheServer;
import com.tarena.douyin.R;
import com.tarena.douyin.app.DouYinApp;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.model.bean.VideoBean;
import com.tarena.douyin.view.adapter.RecommendVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/11/27 13:26
 * @desc 推荐页面
 */
public class RecommendFragment extends BaseFragment {

    //视频缓存代理服务
    private HttpProxyCacheServer proxy;

    @BindView(R.id.vp_video)
    ViewPager2 videoViewPager;

    List<VideoBean> urls = new ArrayList<>();
    private RecommendVideoAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        proxy = DouYinApp.getProxy(getActivity());

        addUrls();

        adapter = new RecommendVideoAdapter(this, urls);
        adapter.setUrls(urls, false);
        videoViewPager.setAdapter(adapter);
        videoViewPager.setOffscreenPageLimit(5);

        videoViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == urls.size() - 5) {
                    addUrls();
                }
              //  adapter.notifyDataSetChanged();
            }
        });
    }

    private void addUrls() {
        // 家有一老如有一宝，不分国界
        VideoBean bean = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fe90000bs854d21rirdcdon9fk0&ratio=720p&line=0",
                "https://p6-dy-ipv6.byteimg.com/img/tos-cn-p-0015/2263f31dfb304120a5fb5d6655b230c5_1594905185~tplv-dmt-logom:tos-cn-i-0813/65302cd29d2d4043a98de10a7723d33d.image?from=2563711402_large"
        );
        VideoBean bean2 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f670000bomln43d82dvbadk5a00&ratio=720p&line=0",
                "https://p29-dy.byteimg.com/obj/tos-cn-p-0015/f61dcb8127204a8cb7a322bf816c0b3e_1580030882?from=2563711402_large"
        );

        VideoBean bean3 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f4e0000bddrstnff778g23hs6mg&ratio=720p&line=0",
                "https://p6-dy-ipv6.byteimg.com/obj/9dfb0003c8c228b763eb?from=2563711402_large"
        );
        VideoBean bean4 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fe10000bloib0nrri6bf7b1k4fg&ratio=720p&line=0",
                "https://p29-dy.byteimg.com/obj/tos-cn-p-0015/601c6d730167431184c5412e81cd32d1?from=2563711402_large"
        );
        VideoBean bean5 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f730000bpebr0dqg5balrfhqlog&ratio=720p&line=0",
                "https://p3-dy-ipv6.byteimg.com/obj/tos-cn-p-0015/0e99f0aca9764e7da53be1096a3bd641_1583136211?from=2563711402_large"
        );
        VideoBean bean6 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200f250000bgsu5vamac2seo2gp53g&ratio=720p&line=0",
                "https://p1-dy-ipv6.byteimg.com/obj/160b4000aa3f373bd14cd?from=2563711402_large"
        );
        VideoBean bean7 = new VideoBean(
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0300f9a0000belnavkqn5hfpb70b5kg&ratio=720p&line=0",
                "https://p29-dy.byteimg.com/obj/c8f200068c30b23f1024?from=2563711402_large"
        );
        urls.add(bean);
        urls.add(bean2);
        urls.add(bean3);
        urls.add(bean4);
        urls.add(bean5);
        urls.add(bean7);

        for (int i = 0; i <urls.size(); i++) {
            // 缓存每个视频的10秒钟
            proxy.preLoad(urls.get(i).getVideoRes(), 10);
        }
    }
}