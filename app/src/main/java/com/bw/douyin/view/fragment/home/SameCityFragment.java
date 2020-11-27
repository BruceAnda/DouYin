package com.bw.douyin.view.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.douyin.R;

/**
 *  @auther Dell Precision
 *  @date 2020/11/27 13:19
 *  @desc 同城Fragment
 *  @version 1.0
 */
public class SameCityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_same_city, container, false);
    }
}