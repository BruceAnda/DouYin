package com.tarena.douyin.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tarena.douyin.view.fragment.MainFragment;
import com.tarena.douyin.view.fragment.PersonalHomeFragment;

public class DouYinMainAdapter extends FragmentStateAdapter {

    public DouYinMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new PersonalHomeFragment();
        }
        return new MainFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
