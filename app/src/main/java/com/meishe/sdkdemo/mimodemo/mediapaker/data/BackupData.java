package com.meishe.sdkdemo.mimodemo.mediapaker.data;


import android.net.Uri;


import com.meishe.sdkdemo.mimodemo.common.dataInfo.CaptionInfo;
import com.meishe.sdkdemo.mimodemo.common.dataInfo.ClipInfo;
import com.meishe.sdkdemo.mimodemo.common.dataInfo.CompoundCaptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/7/11.
 */

public class BackupData {
    private static BackupData mAssetDataBackup;
    private ArrayList<CaptionInfo> mCaptionArrayList;
    private ArrayList<ClipInfo> mClipInfoArray;
    private ArrayList<CompoundCaptionInfo> mCompoundCaptionList;
    private int mCaptionZVal;
    private long m_curSeekTimelinePos = 0;//贴纸和字幕使用
    private int m_clipIndex;//片段编辑专用
    private ArrayList<String> mPicInPicVideoArray;//画中画页面使用

    private ArrayList<ClipInfo> mAddClipInfoList;//只在EditActivity点击添加视频使用

    public ArrayList<String> getPicInPicVideoArray() {
        return mPicInPicVideoArray;
    }

    public void setPicInPicVideoArray(ArrayList<String> picInPicVideoArray) {
        this.mPicInPicVideoArray = picInPicVideoArray;
    }

    public ArrayList<ClipInfo> getAddClipInfoList() {
        return mAddClipInfoList;
    }

    public void setAddClipInfoList(ArrayList<ClipInfo> addClipInfoList) {
        this.mAddClipInfoList = addClipInfoList;
    }
    public void clearAddClipInfoList() {
        mAddClipInfoList.clear();
    }
    public int getClipIndex() {
        return m_clipIndex;
    }

    public void setClipIndex(int clipIndex) {
        this.m_clipIndex = clipIndex;
    }

    public long getCurSeekTimelinePos() {
        return m_curSeekTimelinePos;
    }

    public void setCurSeekTimelinePos(long curSeekTimelinePos) {
        this.m_curSeekTimelinePos = curSeekTimelinePos;
    }

    public int getCaptionZVal() {
        return mCaptionZVal;
    }

    public void setCaptionZVal(int captionZVal) {
        this.mCaptionZVal = captionZVal;
    }

    public void setCaptionData(ArrayList<CaptionInfo> captionArray) {
        mCaptionArrayList = captionArray;
    }
    public ArrayList<CaptionInfo> getCaptionData() {
        return mCaptionArrayList;
    }

    public ArrayList<CaptionInfo> cloneCaptionData() {
        ArrayList<CaptionInfo> newList = new ArrayList<>();
        for(CaptionInfo captionInfo:mCaptionArrayList) {
            CaptionInfo newCaptionInfo = captionInfo.clone();
            newList.add(newCaptionInfo);
        }
        return newList;
    }

    public void setClipInfoData(ArrayList<ClipInfo> clipInfoArray) {
        this.mClipInfoArray = clipInfoArray;
    }

    public ArrayList<ClipInfo> getClipInfoData() {
        return mClipInfoArray;
    }

    public ArrayList<ClipInfo> cloneClipInfoData() {
        ArrayList<ClipInfo> newArrayList = new ArrayList<>();
        for(ClipInfo clipInfo:mClipInfoArray) {
            ClipInfo newClipInfo = clipInfo.clone();
            newArrayList.add(newClipInfo);
        }
        return newArrayList;
    }

    public ArrayList<CompoundCaptionInfo> getCompoundCaptionList() {
        return mCompoundCaptionList;
    }

    public void setCompoundCaptionList(ArrayList<CompoundCaptionInfo> compoundCaptionList) {
        this.mCompoundCaptionList = compoundCaptionList;
    }

    public ArrayList<CompoundCaptionInfo> cloneCompoundCaptionData() {
        ArrayList<CompoundCaptionInfo> newList = new ArrayList<>();
        for(CompoundCaptionInfo captionInfo:mCompoundCaptionList) {
            CompoundCaptionInfo newCaptionInfo = captionInfo.clone();
            newList.add(newCaptionInfo);
        }
        return newList;
    }

    public static BackupData init() {
        if (mAssetDataBackup == null) {
            synchronized (BackupData.class){
                if (mAssetDataBackup == null) {
                    mAssetDataBackup = new BackupData();
                }
            }
        }
        return mAssetDataBackup;
    }

    public void clear() {
        mCaptionArrayList.clear();
        clearAddClipInfoList();
        mClipInfoArray.clear();
        if(mCompoundCaptionList != null){
            mCompoundCaptionList.clear();
        }
        mCaptionZVal = 0;
        m_clipIndex = 0;
        m_curSeekTimelinePos = 0;
    }

    public static BackupData instance() {
        if (mAssetDataBackup == null)
            mAssetDataBackup = new BackupData();
        return mAssetDataBackup;
    }
    private BackupData() {
        mCaptionArrayList = new ArrayList<>();
        mClipInfoArray = new ArrayList<>();
        mAddClipInfoList = new ArrayList<>();
        mPicInPicVideoArray = new ArrayList<>();
        mCompoundCaptionList = new ArrayList<>();
        mCaptionZVal = 0;
        m_clipIndex = 0;
        m_curSeekTimelinePos = 0;
    }
    private static Map<String, Uri> mMediaUriToCache = new HashMap();
    public static void addMediaUriToCache(String path, Uri thumbPathUri) {
        String key = String.valueOf(path.hashCode());
        if (!mMediaUriToCache.containsKey(key)) {
            mMediaUriToCache.put(key, thumbPathUri);
        }
    }

    public static Uri getThumbPathUriFromCache(String path) {
        return mMediaUriToCache.get(String.valueOf(path.hashCode()));
    }
}
