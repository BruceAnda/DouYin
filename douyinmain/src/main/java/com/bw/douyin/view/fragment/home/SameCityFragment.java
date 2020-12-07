package com.bw.douyin.view.fragment.home;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bw.douyin.R;
import com.bw.douyin.base.BaseFragment;
import com.bw.douyin.model.api.APIService;
import com.bw.douyin.model.bean.SameCityBean;
import com.bw.douyin.view.adapter.SameCityAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/11/27 13:19
 * @desc 同城Fragment
 */
public class SameCityFragment extends BaseFragment {

    @BindView(R.id.rv_same_city_list)
    RecyclerView rvSameCityList;
    private SameCityAdapter adapter;
    private StaggeredGridLayoutManager manager;

    private int[] lastPositions;
    private int lastVisibleItemPosition;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_same_city;
    }

    @Override
    protected void init() {
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSameCityList.setLayoutManager(manager);
        adapter = new SameCityAdapter();
        rvSameCityList.setAdapter(adapter);

        rvSameCityList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    if (lastPositions == null) {
                        lastPositions = new int[manager.getSpanCount()];
                    }
                    manager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItemPosition = findMax(lastPositions);
                    if(lastVisibleItemPosition >= manager.getItemCount() - 2){

//                        System.out.println("====自动加载");
                        getDataFromServer();
                    }
                }
            }
        });

    }

    /**
     * 取数组中最大值
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    @Override
    protected void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        APIService.getInstance().getSameCityData(1, 10, new APIService.APIServiceCallback<List<SameCityBean>, String>() {
            @Override
            public void onSuccess(List<SameCityBean> response) {
                adapter.setDatas(response, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }
}