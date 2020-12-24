package com.tarena.douyin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.tarena.douyin.view.fragment.MainFragment;
import com.tarena.douyin.view.fragment.PersonalHomeFragment;
import com.tarena.douyin.view.widget.MyViewPager;
import com.meicam.sdk.NvsStreamingContext;
import com.meishe.sdkdemo.MSApplication;
import com.meishe.sdkdemo.ParameterSettingActivity;
import com.meishe.sdkdemo.base.BaseFragmentPagerAdapter;
import com.meishe.sdkdemo.base.BasePermissionActivity;
import com.meishe.sdkdemo.boomrang.BoomRangActivity;
import com.meishe.sdkdemo.capture.CaptureActivity;
import com.meishe.sdkdemo.capturescene.CaptureSceneActivity;
import com.meishe.sdkdemo.capturescene.httputils.NetWorkUtil;
import com.meishe.sdkdemo.capturescene.httputils.OkHttpClientManager;
import com.meishe.sdkdemo.capturescene.httputils.ResultCallback;
import com.meishe.sdkdemo.dialog.PrivacyPolicyDialog;
import com.meishe.sdkdemo.douvideo.DouVideoCaptureActivity;
import com.meishe.sdkdemo.edit.data.BackupData;
import com.meishe.sdkdemo.edit.watermark.SingleClickActivity;
import com.meishe.sdkdemo.feedback.FeedBackActivity;
import com.meishe.sdkdemo.glitter.GlitterEffectActivity;
import com.meishe.sdkdemo.main.MainViewPagerFragment;
import com.meishe.sdkdemo.main.MainViewPagerFragmentData;
import com.meishe.sdkdemo.main.MainWebViewActivity;
import com.meishe.sdkdemo.main.OnItemClickListener;
import com.meishe.sdkdemo.main.SpannerViewpagerAdapter;
import com.meishe.sdkdemo.main.bean.AdBeansFormUrl;
import com.meishe.sdkdemo.mimodemo.MyStoryActivity;
import com.meishe.sdkdemo.mimodemo.common.utils.MeicamContextWrap;
import com.meishe.sdkdemo.musicLyrics.MultiVideoSelectActivity;
import com.meishe.sdkdemo.particle.ParticleCaptureActivity;
import com.meishe.sdkdemo.photoalbum.PhotoAlbumActivity;
import com.meishe.sdkdemo.selectmedia.SelectMediaActivity;
import com.meishe.sdkdemo.superzoom.SuperZoomActivity;
import com.meishe.sdkdemo.themeshoot.ThemeSelectActivity;
import com.meishe.sdkdemo.utils.AppManager;
import com.meishe.sdkdemo.utils.Constants;
import com.meishe.sdkdemo.utils.FileUtils;
import com.meishe.sdkdemo.utils.Logger;
import com.meishe.sdkdemo.utils.MediaConstant;
import com.meishe.sdkdemo.utils.ParameterSettingValues;
import com.meishe.sdkdemo.utils.ScreenUtils;
import com.meishe.sdkdemo.utils.SpUtil;
import com.meishe.sdkdemo.utils.SystemUtils;
import com.meishe.sdkdemo.utils.ToastUtil;
import com.meishe.sdkdemo.utils.Util;
import com.meishe.sdkdemo.utils.dataInfo.TimelineData;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JzvdStd;
import okhttp3.Request;

import static com.meishe.sdkdemo.utils.Constants.BUILD_HUMAN_AI_TYPE_FU;
import static com.meishe.sdkdemo.utils.Constants.BUILD_HUMAN_AI_TYPE_MS;
import static com.meishe.sdkdemo.utils.Constants.BUILD_HUMAN_AI_TYPE_MS_ST;
import static com.meishe.sdkdemo.utils.Constants.HUMAN_AI_TYPE_MS;
import static com.meishe.sdkdemo.utils.Constants.HUMAN_AI_TYPE_NONE;

public class DouYinMainActivity extends BasePermissionActivity implements OnItemClickListener {

    private static final String TAG = "MainActivity";
    public static final String AD_SPANNER_URL = "https://vsapi.meishesdk.com/app/index.php?command=listAdvertisement&page=0&pageSize=10";
    public static final int REQUEST_CAMERA_PERMISSION_CODE = 200;
    public static final int INIT_ARSCENE_COMPLETE_CODE = 201;
    public static final int INIT_ARSCENE_FAILURE_CODE = 202;
    public static final int AD_SPANNER_CHANGE_CODE = 203;
    private ImageView mIvSetting;
    private ImageView mIvFeedBack;
    private RelativeLayout layoutVideoCapture;
    private RelativeLayout layoutVideoEdit;
    private ViewPager mainViewPager;
    private RadioGroup radioGroup;
    private TextView mainVersionNumber;
    private int spanCount = 8;
    private View clickedView = null;

    /*
     * 人脸初始化完成的标识
     * Face initialization completed logo_douyin
     * */
    private boolean arSceneFinished = false;
    /*
     * 记录人脸模块正在初始化
     * Recording face module is initializing
     * */
    private boolean initARSceneing = true;
    /*
     * 防止页面重复点击标识
     * Prevent pages from repeatedly clicking on logos
     * */
    private boolean isClickRepeat = false;

    /**
     * SDK普通版
     * <p>
     * SDK Normal Edition
     */
    private int mCanUseARFaceType = HUMAN_AI_TYPE_NONE;

    private HandlerThread mHandlerThread;

    private MainActivityHandler mHandler = new MainActivityHandler(this);
    private ViewPager mBannerViewPager;
    private SpannerViewpagerAdapter mSpannerViewpagerAdapter;
    private List<AdBeansFormUrl.AdInfo> mAdInfos = new ArrayList<>();
    private Runnable mAdRunnable;

    class MainActivityHandler extends Handler {
        WeakReference<DouYinMainActivity> mWeakReference;

        public MainActivityHandler(DouYinMainActivity mainActivityContext) {
            mWeakReference = new WeakReference<>(mainActivityContext);
        }

        @Override
        public void handleMessage(Message msg) {
            final DouYinMainActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case INIT_ARSCENE_COMPLETE_CODE:
                        /*
                         *  初始化ARScene 完成
                         * Initialization of ARScene completed
                         * */
                        arSceneFinished = true;
                        initARSceneing = false;
                        break;
                    case INIT_ARSCENE_FAILURE_CODE:
                        /*
                         *  初始化ARScene 失败
                         * Initializing ARScene failed
                         * */
                        arSceneFinished = false;
                        initARSceneing = false;
                        break;
                    case AD_SPANNER_CHANGE_CODE:
                        /*
                         * 广告切换
                         * Advertising switch
                         * */
                        if ((mBannerViewPager != null) && (mAdInfos != null) && (mAdInfos.size() > 1)) {
                            mBannerViewPager.setCurrentItem(mBannerViewPager.getCurrentItem() + 1);
                        }
                        break;
                    default:
                        break;

                }
            }
        }
    }

    @Override
    protected int initRootView() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return R.layout.activity_main;
        }
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    /**
     * 存放底部导航页面 和 个人中心页面的ViewPager2
     */
    MyViewPager mViewPager;
    public void setCanScroll(boolean canScroll) {
        if (mViewPager != null) {
            mViewPager.setCanScroll(canScroll);
        }
    }

    @Override
    protected void initViews() {
        mViewPager = findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new MainFragment();
                } else {
                    return new PersonalHomeFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    JzvdStd.goOnPlayOnPause();
                } else {
                    JzvdStd.goOnPlayOnResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIvSetting = (ImageView) findViewById(com.meishe.sdkdemo.R.id.iv_main_setting);
        mIvFeedBack = (ImageView) findViewById(com.meishe.sdkdemo.R.id.iv_main_feedback);
        layoutVideoCapture = (RelativeLayout) findViewById(com.meishe.sdkdemo.R.id.layout_video_capture);
        layoutVideoEdit = (RelativeLayout) findViewById(com.meishe.sdkdemo.R.id.layout_video_edit);
        mainViewPager = (ViewPager) findViewById(com.meishe.sdkdemo.R.id.main_viewPager);
        radioGroup = (RadioGroup) findViewById(com.meishe.sdkdemo.R.id.main_radioGroup);
        mainVersionNumber = (TextView) findViewById(com.meishe.sdkdemo.R.id.main_versionNumber);
        mBannerViewPager = (ViewPager) findViewById(com.meishe.sdkdemo.R.id.banner_viewpager);
    }

    @Override
    protected void initData() {
        MeicamContextWrap.getInstance().setContext(this.getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int alwaysFinish = Settings.Global.getInt(getContentResolver(), Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0);
            if (alwaysFinish == 1) {
                Dialog dialog = null;
                dialog = new AlertDialog.Builder(this)
                        .setMessage(com.meishe.sdkdemo.R.string.no_back_activity_message)
                        .setNegativeButton(com.meishe.sdkdemo.R.string.no_back_activity_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                DouYinMainActivity.this.finish();
                            }
                        }).setPositiveButton(com.meishe.sdkdemo.R.string.no_back_activity_setting, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                                startActivity(intent);
                            }
                        }).create();
                dialog.show();
            }
        }
        ParameterSettingValues parameterValues = (ParameterSettingValues) SpUtil.getObjectFromShare(getApplicationContext(), Constants.KEY_PARAMTER);
        /*
         * 本地没有存储设置的参数，设置默认值
         * There is no parameter stored locally, set the default value
         * */
        if (parameterValues != null) {
            ParameterSettingValues.setParameterValues(parameterValues);
        }
        initFragmentAndView();
        NvsStreamingContext.SdkVersion sdkVersion = NvsStreamingContext.getInstance().getSdkVersion();
        String sdkVersionNum = String.valueOf(sdkVersion.majorVersion) + "." + String.valueOf(sdkVersion.minorVersion) + "." + String.valueOf(sdkVersion.revisionNumber);
        mainVersionNumber.setText(String.format(getResources().getString(com.meishe.sdkdemo.R.string.versionNumber), sdkVersionNum));
        initSpannerViewData();
        mAdRunnable = new AdRunnable();
    }

    private void initSpannerViewData() {
        SystemUtils.isZh(DouYinMainActivity.this);
        mSpannerViewpagerAdapter = new SpannerViewpagerAdapter(DouYinMainActivity.this, mAdInfos);
        mBannerViewPager.setAdapter(mSpannerViewpagerAdapter);
        int item = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % (mAdInfos.size() == 0 ? 1 : mAdInfos.size()));
        mBannerViewPager.setCurrentItem(item);
        if (NetWorkUtil.isNetworkConnected(this)) {
            OkHttpClientManager.getAsyn(AD_SPANNER_URL, new ResultCallback<AdBeansFormUrl>() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(AdBeansFormUrl response) {
                    if ((response != null) && (response.getErrNo() == 0)) {
                        mAdInfos = response.getList();
                        setSpannerListener();
                        if (mSpannerViewpagerAdapter != null) {
                            mSpannerViewpagerAdapter.setAdapterData(mAdInfos);
                            /*
                             * 开启广告定时轮播
                             * Turn on ad timing rotation
                             * */
                            if ((mAdRunnable != null) && (mAdInfos.size() > 1)) {
                                mHandler.removeCallbacks(mAdRunnable);
                                mHandler.postDelayed(mAdRunnable, 5000);
                            }
                        }
                    }
                }
            });
        }
    }

    class AdRunnable implements Runnable {
        @Override
        public void run() {
            if (mHandler != null) {
                mHandler.sendEmptyMessage(AD_SPANNER_CHANGE_CODE);
                mHandler.postDelayed(this, 5000);
            }
        }
    }

    private void setSpannerListener() {
        if (mSpannerViewpagerAdapter != null) {
            mSpannerViewpagerAdapter.setSpannerClickCallback(new SpannerViewpagerAdapter.SpannerClickCallback() {
                @Override
                public void spannerClick(int position, AdBeansFormUrl.AdInfo adInfo) {
                    if (Util.isFastClick()) {
                        return;
                    }
                    boolean notNull = (adInfo != null) && (!TextUtils.isEmpty(adInfo.getAdvertisementUrl())) && (!TextUtils.isEmpty(adInfo.getAdvertisementUrlEn()));
                    if (notNull) {
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", SystemUtils.isZh(getApplicationContext()) ? adInfo.getAdvertisementUrl() : adInfo.getAdvertisementUrlEn());
                        Intent intent = new Intent(DouYinMainActivity.this, MainWebViewActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent, bundle);
                    }
                }
            });
        }
    }

    private void initFragmentAndView() {
        /*
         * 按照每页个数，生成索引和名称，关系映射
         * According to the number of pages, generate the index and name, and the relationship mapping
         * */
        Map<Integer, List<String>> map = subListByItemCount();
        List<Fragment> mFragmentList = getSupportFragmentManager().getFragments();
        if (mFragmentList == null || mFragmentList.size() == 0) {
            mFragmentList = new ArrayList<>();
            for (int i = 0; i < map.size(); i++) {
                List<String> nameList = map.get(i);
                MainViewPagerFragment mediaFragment = new MainViewPagerFragment();
                Bundle bundle = new Bundle();
                /*
                 *  功能图标，实体类集合
                 * Function icon, entity class collection
                 * */
                ArrayList<MainViewPagerFragmentData> list = initFragmentDataById(nameList, i);
                bundle.putParcelableArrayList("list", list);
//              bundle.putInt("span", spanCount);
                mediaFragment.setArguments(bundle);
                mFragmentList.add(mediaFragment);
            }
        }
        for (int i = 0; i < map.size(); i++) {
            addRadioButton(i);
        }
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList, null);
        mainViewPager.setAdapter(fragmentPagerAdapter);
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setRadioButtonState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 生成每页显示的功能标题集合
     * <p>
     * Generate a collection of feature titles displayed per page
     *
     * @return 索引和对应索引页的标题集合；Index and title set of corresponding index pages
     */
    private Map<Integer, List<String>> subListByItemCount() {
        String[] fragmentItems = getResources().getStringArray(com.meishe.sdkdemo.R.array.main_fragment_item);
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> list = Arrays.asList(fragmentItems);
        int count = list.size() / spanCount + 1;
        for (int i = 0; i < count; i++) {
            int endTime = list.size() < (i + 1) * spanCount ? list.size() : (i + 1) * spanCount;
            int startTime = i == 0 ? i : i * spanCount;
            List<String> childList = list.subList(startTime, endTime);
            map.put(i, childList);
        }
        return map;
    }

    private void addRadioButton(int i) {
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(ScreenUtils.dip2px(this, 5), ScreenUtils.dip2px(this, 5));
        lp.setMargins(0, 0, ScreenUtils.dip2px(this, 5), 0);
        radioGroup.addView(initRadioButton(i), lp);
    }

    private RadioButton initRadioButton(int i) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setId(getResources().getIdentifier("main_radioButton" + i, "id", getPackageName()));
        radioButton.setBackground(getResources().getDrawable(com.meishe.sdkdemo.R.drawable.activity_main_checkbox_background));
        radioButton.setButtonDrawable(null);
        radioButton.setChecked(i == 0);
        return radioButton;
    }

    /**
     * @param names
     * @param fragmentCount 当前功能页索引；Index of current feature page
     * @return
     */
    private ArrayList<MainViewPagerFragmentData> initFragmentDataById(List<String> names, int fragmentCount) {
        /*
         * 当前页功能模块背景
         * Function page background of current page
         * */
        String[] fragmentItemsBackGround = getResources().getStringArray(com.meishe.sdkdemo.R.array.main_fragment_background);
        List<String> listBackground = Arrays.asList(fragmentItemsBackGround);

        /*
         * 当前页功能模块图标
         * Current page function module icon
         * */
        String[] fragmentItemsImage = getResources().getStringArray(com.meishe.sdkdemo.R.array.main_fragment_image);
        List<String> listImage = Arrays.asList(fragmentItemsImage);

        /*
         * 生成当前页面功能模块，实体类集合
         * Generate current page function module, entity class collection
         * */
        ArrayList<MainViewPagerFragmentData> list1 = new ArrayList<>();
        for (int i = 0, size = names.size(); i < size; i++) {
            int backGroundId = getResources().getIdentifier(listBackground.get(fragmentCount * 8 + i), "drawable", getPackageName());
            int imageId = getResources().getIdentifier(listImage.get(fragmentCount * 8 + i), "drawable", getPackageName());
            if (backGroundId != 0 && imageId != 0) {
                list1.add(new MainViewPagerFragmentData(backGroundId, names.get(i), imageId));
            }
        }
        return list1;
    }

    private void setRadioButtonState(int position) {
        RadioButton radioButton = (RadioButton) findViewById(getResources().getIdentifier("main_radioButton" + position, "id", getPackageName()));
        radioButton.setChecked(true);
    }

    @Override
    protected void initListener() {
        mIvSetting.setOnClickListener(this);
        mIvFeedBack.setOnClickListener(this);
        layoutVideoCapture.setOnClickListener(this);
        layoutVideoEdit.setOnClickListener(this);
        checkPermissions();
        if (hasAllPermission()) {
            /*
             * 初始化人脸Model
             * Initialize Face Model
             * */
            initARSceneEffect();
            showPrivacyDialog();
        }
    }

    @Override
    public void onClick(View view) {
        if (isClickRepeat) {
            return;
        }
        isClickRepeat = true;
        int id = view.getId();/*
         * 设置
         * Set up
         * */
        if (id == com.meishe.sdkdemo.R.id.iv_main_setting) {
            AppManager.getInstance().jumpActivity(this, ParameterSettingActivity.class, null);
            return;
            /*
             * 反馈
             * Feedback
             * */
        } else if (id == com.meishe.sdkdemo.R.id.iv_main_feedback) {
            AppManager.getInstance().jumpActivity(this, FeedBackActivity.class, null);
            return;
        }
        /*
         * 没有权限，则请求权限
         * No permission, request permission
         * */
        if (!hasAllPermission()) {
            clickedView = view;
            checkPermissions();
        } else {
            doClick(view);
        }
    }

    private void initARSceneEffect() {
        mCanUseARFaceType = NvsStreamingContext.hasARModule();
        /*
         *  初始化AR Scene，全局只需一次
         * Initialize AR Scene, only once globally
         * */
        if (mCanUseARFaceType == HUMAN_AI_TYPE_MS && !arSceneFinished) {
            if (mHandlerThread == null) {
                mHandlerThread = new HandlerThread("handlerThread");
                mHandlerThread.start();
            }
            Handler initHandler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    String modelPath = null;
                    String licensePath = null;
                    String faceModelName = null;
                    String className = null;
                    if ("MS".equals(BUILD_HUMAN_AI_TYPE_MS)) {
                        modelPath = "/facemode/ms_face_v1.0.1.model";
                        faceModelName = "ms_face_v1.0.1.model";
                        className = "facemode";
                        licensePath = "";
                    } else if ("MS".equals(BUILD_HUMAN_AI_TYPE_MS_ST)) {
                        modelPath = "/facemode/tt_face.model";
                        faceModelName = "tt_face.model";
                        className = "facemode";
                        licensePath = "assets:/facemode/tt_face.license";
                    } else if ("MS".equals(BUILD_HUMAN_AI_TYPE_FU)) {
                        modelPath = "/facemode/fu/fu_face_v3.model";
                        faceModelName = "fu_face_v3.model";
                        className = "facemode/fu";
                        licensePath = "assets:/facemode/fu/fu_face_v3.license";
                    }

                    boolean copySuccess = FileUtils.copyFileIfNeed(DouYinMainActivity.this, faceModelName, className);
                    Logger.e(TAG, "copySuccess-->" + copySuccess);

                    File rootDir = getApplicationContext().getExternalFilesDir(null);
                    String destModelDir = rootDir + modelPath;
                    boolean initSuccess = NvsStreamingContext.initHumanDetection(MSApplication.getmContext(),
                            destModelDir, licensePath,
                            NvsStreamingContext.HUMAN_DETECTION_FEATURE_FACE_LANDMARK | NvsStreamingContext.HUMAN_DETECTION_FEATURE_FACE_ACTION);
                    Logger.e(TAG, "initSuccess-->" + initSuccess);
                    //扩展模型
                    modelPath = rootDir + "/facemode/ms_avatar_1.0.0.model";
                    faceModelName = "ms_avatar_1.0.0.model";
                    FileUtils.copyFileIfNeed(DouYinMainActivity.this, faceModelName, className);
                    NvsStreamingContext.initHumanDetectionExt(MSApplication.getmContext(), modelPath, null, NvsStreamingContext.HUMAN_DETECTION_FEATURE_AVATAR_EXPRESSION);

                    String fakefacePath = "assets:/facemode/fakeface.dat";
                    boolean fakefaceSuccess = NvsStreamingContext.setupHumanDetectionData(NvsStreamingContext.HUMAN_DETECTION_DATA_TYPE_FAKE_FACE, fakefacePath);
                    Logger.e(TAG, "fakefaceSuccess-->" + fakefaceSuccess);
                    String maleupPath = "assets:/facemode/makeup.dat";
                    boolean makeupSuccess = NvsStreamingContext.setupHumanDetectionData(NvsStreamingContext.HUMAN_DETECTION_DATA_TYPE_MAKEUP, maleupPath);
                    Logger.e(TAG, "makeupSuccess-->" + makeupSuccess);
                    if (initSuccess) {
                        mHandler.sendEmptyMessage(INIT_ARSCENE_COMPLETE_CODE);
                    } else {
                        mHandler.sendEmptyMessage(INIT_ARSCENE_FAILURE_CODE);
                    }
                    return false;
                }
            });
            initHandler.sendEmptyMessage(1);
        } else {
            initARSceneing = false;
        }
    }

    private void doClick(View view) {
        if (view == null)
            return;
        int id = view.getId();
        if (id == com.meishe.sdkdemo.R.id.iv_main_setting) {//setting
            AppManager.getInstance().jumpActivity(this, ParameterSettingActivity.class, null);
        } else if (id == com.meishe.sdkdemo.R.id.layout_video_capture) {//Shoot
            if (!initARSceneing) {
                Bundle captureBundle = new Bundle();
                captureBundle.putBoolean("initArScene", arSceneFinished);
                AppManager.getInstance().jumpActivity(this, CaptureActivity.class, captureBundle);
            } else {
                isClickRepeat = false;
                ToastUtil.showToast(DouYinMainActivity.this, com.meishe.sdkdemo.R.string.initArsence);
            }
        } else if (id == com.meishe.sdkdemo.R.id.layout_video_edit) {//Edit
            Bundle editBundle = new Bundle();
            editBundle.putInt("visitMethod", Constants.FROMMAINACTIVITYTOVISIT);
            editBundle.putInt("limitMediaCount", -1);//-1表示无限可选择素材
            AppManager.getInstance().jumpActivity(this, SelectMediaActivity.class, editBundle);
        } else {
            String tag = (String) view.getTag();
            if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.douYinEffects))) {
                if (!initARSceneing) {
                    Bundle douyinBundle = new Bundle();
                    douyinBundle.putBoolean("initArScene", arSceneFinished);
                    douyinBundle.putInt(DouVideoCaptureActivity.INTENT_KEY_STRENGTH, 75);
                    if (arSceneFinished) {
                        douyinBundle.putInt(DouVideoCaptureActivity.INTENT_KEY_CHEEK, 150);
                        douyinBundle.putInt(DouVideoCaptureActivity.INTENT_KEY_EYE, 150);
                    }
                    AppManager.getInstance().jumpActivity(this, DouVideoCaptureActivity.class, douyinBundle);
                } else {
                    isClickRepeat = false;
                    ToastUtil.showToast(DouYinMainActivity.this, com.meishe.sdkdemo.R.string.initArsence);
                }
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.particleEffects))) {
                AppManager.getInstance().jumpActivity(this, ParticleCaptureActivity.class, null);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.captureScene))) {
                AppManager.getInstance().jumpActivity(this, CaptureSceneActivity.class, null);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.picInPic))) {
                Bundle pipBundle = new Bundle();
                pipBundle.putInt("visitMethod", Constants.FROMPICINPICACTIVITYTOVISIT);
                /*
                 * 2表示选择两个素材
                 * 2 means select two materials
                 * */
                pipBundle.putInt("limitMediaCount", 2); //
                AppManager.getInstance().jumpActivity(this, SelectMediaActivity.class, pipBundle);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.makingCover))) {
                Bundle makeCoverBundle = new Bundle();
                makeCoverBundle.putInt(Constants.SELECT_MEDIA_FROM, Constants.SELECT_IMAGE_FROM_MAKE_COVER);
                AppManager.getInstance().jumpActivity(this, SingleClickActivity.class, makeCoverBundle);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.flipSubtitles))) {
                Bundle flipBundle = new Bundle();
                flipBundle.putInt(Constants.SELECT_MEDIA_FROM, Constants.SELECT_VIDEO_FROM_FLIP_CAPTION);
                /*
                 * -1表示无限可选择素材
                 * -1 means unlimited selectable material
                 * */
                flipBundle.putInt("limitMediaCount", -1);
                flipBundle.putInt(MediaConstant.MEDIA_TYPE, MediaConstant.VIDEO);
                AppManager.getInstance().jumpActivity(this, MultiVideoSelectActivity.class, flipBundle);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.musicLyrics))) {
                Bundle musicBundle = new Bundle();
                musicBundle.putInt(Constants.SELECT_MEDIA_FROM, Constants.SELECT_VIDEO_FROM_MUSIC_LYRICS);
                /*
                 * -1表示无限可选择素材
                 * -1 means unlimited selectable material
                 * */
                musicBundle.putInt("limitMediaCount", -1);
                musicBundle.putInt(MediaConstant.MEDIA_TYPE, MediaConstant.VIDEO);
                AppManager.getInstance().jumpActivity(this, MultiVideoSelectActivity.class, musicBundle);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.boomRang))) {
                AppManager.getInstance().jumpActivity(this, BoomRangActivity.class);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.pushMirrorFilm))) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    AppManager.getInstance().jumpActivity(this, SuperZoomActivity.class);
                } else {
                    String[] tipsInfo = getResources().getStringArray(com.meishe.sdkdemo.R.array.edit_function_tips);
                    Util.showDialog(DouYinMainActivity.this, tipsInfo[0], getString(com.meishe.sdkdemo.R.string.versionBelowTip));
                }
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.photosAlbum))) {
                AppManager.getInstance().jumpActivity(this, PhotoAlbumActivity.class, null);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.flashEffect))) {
                AppManager.getInstance().jumpActivity(this, GlitterEffectActivity.class, null);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.mimo))) {
                AppManager.getInstance().jumpActivity(this, MyStoryActivity.class, null);
            } else if (tag.equals(getResources().getString(com.meishe.sdkdemo.R.string.theme_shoot))) {
                AppManager.getInstance().jumpActivity(AppManager.getInstance().currentActivity(), ThemeSelectActivity.class, null);
            } else {
                String[] tipsInfo = getResources().getStringArray(com.meishe.sdkdemo.R.array.edit_function_tips);
                Util.showDialog(DouYinMainActivity.this, tipsInfo[0], tipsInfo[1], tipsInfo[2]);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandlerThread != null) {
            mHandlerThread.quit();
            mHandlerThread = null;
        }
        if (hasAllPermission()) {
            Util.clearRecordAudioData();
        }
        // 退出清理
        if (mStreamingContext != null) {
            if (mCanUseARFaceType == HUMAN_AI_TYPE_MS) {
                NvsStreamingContext.closeHumanDetection();
            }
            NvsStreamingContext.close();
            mStreamingContext = null;
            TimelineData.instance().clear();
            BackupData.instance().clear();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isClickRepeat = false;
    }

    /**
     * 获取activity需要的权限列表
     * Get the list of permissions required by the activity
     *
     * @return 权限列表;Permission list
     */
    @Override
    protected List<String> initPermissions() {
        return Util.getAllPermissionsList();
    }

    /**
     * 获取权限
     * Get permission
     */
    @Override
    protected void hasPermission() {
        Log.e(TAG, "hasPermission: 所有权限都有了");
        /*
         * 初始化人脸Model
         * Initialize Face Model
         * */
        initARSceneEffect();
        doClick(clickedView);
        showPrivacyDialog();
    }

    /**
     * 没有允许权限
     * No permission
     */
    @Override
    protected void nonePermission() {
        Log.e(TAG, "hasPermission: 没有允许权限");
    }

    /**
     * 用户选择了不再提示
     * The user chose not to prompt again
     */
    @Override
    protected void noPromptPermission() {
        Log.e(TAG, "hasPermission: 用户选择了不再提示");
        startAppSettings();
    }

    /*
     * 启动应用的设置
     * Launch app settings
     * */
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MobclickAgent.onKillProcess(this);
    }

    private void showPrivacyDialog() {
        final SpUtil spUtil = SpUtil.getInstance(getApplicationContext());
        boolean isAgreePrivacy = spUtil.getBoolean(Constants.KEY_AGREE_PRIVACY, false);
        if (!isAgreePrivacy) {
            PrivacyPolicyDialog privacyPolicyDialog = new PrivacyPolicyDialog(DouYinMainActivity.this, com.meishe.sdkdemo.R.style.dialog);
            privacyPolicyDialog.setOnButtonClickListener(new PrivacyPolicyDialog.OnPrivacyClickListener() {
                @Override
                public void onButtonClick(boolean isAgree) {
                    spUtil.putBoolean(Constants.KEY_AGREE_PRIVACY, isAgree);
                    if (!isAgree) {
                        AppManager.getInstance().finishActivity();
                    }
                }

                @Override
                public void pageJumpToWeb(String clickTextContent) {
                    String serviceAgreement = getString(com.meishe.sdkdemo.R.string.service_agreement);
                    String privacyPolicy = getString(com.meishe.sdkdemo.R.string.privacy_policy);
                    String visitUrl = "";
                    if (clickTextContent.contains(serviceAgreement)) {
                        visitUrl = Constants.SERVICE_AGREEMENT_URL;
                    } else if (clickTextContent.contains(privacyPolicy)) {
                        visitUrl = Constants.PRIVACY_POLICY_URL;
                    }
                    if (TextUtils.isEmpty(visitUrl)) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("URL", visitUrl);
                    AppManager.getInstance().jumpActivity(DouYinMainActivity.this, MainWebViewActivity.class, bundle);
                }
            });
           // privacyPolicyDialog.show();
        }
    }
}
