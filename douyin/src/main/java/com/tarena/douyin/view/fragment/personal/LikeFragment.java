package com.tarena.douyin.view.fragment.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tarena.douyin.R;

/**
 *  @auther Dell Precision
 *  @date 2020/11/29 16:01
 *  @desc 喜欢Fragment
 *  @version 1.0
 */
public class LikeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false);
    }
}