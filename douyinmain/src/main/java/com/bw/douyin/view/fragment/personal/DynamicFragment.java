package com.bw.douyin.view.fragment.personal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;

/**
 *  @auther Dell Precision
 *  @date 2020/11/29 16:01
 *  @desc 动态Fragment
 *  @version 1.0
 */
public class DynamicFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }
}