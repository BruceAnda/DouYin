package com.tarena.douyin.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.tarena.douyin.R;
import com.tarena.douyin.model.bean.SameCityBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/12/2 15:25
 * @desc 同城页面适配器
 */
public class SameCityAdapter extends RecyclerView.Adapter<SameCityAdapter.ViewHolder> {

    private int[] itemHeight = {ScreenUtils.getAppScreenHeight()/2 - 100, ScreenUtils.getAppScreenHeight()/2, ScreenUtils.getAppScreenHeight()/2 + 100, ScreenUtils.getAppScreenHeight()/2};
    private List<SameCityBean> datas = new ArrayList<>();

    public SameCityAdapter() {
    }

    /**
     * 设置数据集
     * @param data
     * @param needClear
     */
    public void setDatas(List<SameCityBean> data, boolean needClear) {
        if (needClear) {
            datas.clear();
        }
        datas.addAll(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_same_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sdvImage.setImageURI(datas.get(position).getImagePic());
        holder.sdvAvatar.setImageURI(datas.get(position).getAvatar());
        holder.tvDistance.setText(datas.get(position).getDistance());

        // 动态改变图片高度实现瀑布流
        ViewGroup.LayoutParams layoutParams = holder.sdvImage.getLayoutParams();
        layoutParams.height = itemHeight[position % itemHeight.length];
        holder.sdvImage.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdv_image)
        SimpleDraweeView sdvImage;
        @BindView(R.id.sdv_avatar)
        SimpleDraweeView sdvAvatar;
        @BindView(R.id.tv_distance)
        TextView tvDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
