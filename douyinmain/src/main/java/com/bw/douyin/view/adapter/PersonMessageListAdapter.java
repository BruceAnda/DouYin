package com.bw.douyin.view.adapter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.douyin.R;
import com.bw.douyin.model.bean.PersonMessageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/12/2 20:30
 * @desc 推荐朋友适配器
 */
public class PersonMessageListAdapter extends RecyclerView.Adapter<PersonMessageListAdapter.ViewHolder> {

    private List<PersonMessageBean> datas = new ArrayList<>();

    public PersonMessageListAdapter() {
    }

    /**
     * 设置数据集
     *
     * @param data
     * @param needClear
     */
    public void setDatas(List<PersonMessageBean> data, boolean needClear) {
        if (needClear) {
            datas.clear();
        }
        datas.addAll(data);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sdvAvatar.setImageURI("res://com.bw.douyin/" + datas.get(position).getIcon());
        holder.tvName.setText(datas.get(position).getNikeName());
        holder.tvMessage.setText(datas.get(position).getFollowMessage());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdv_avatar)
        SimpleDraweeView sdvAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
