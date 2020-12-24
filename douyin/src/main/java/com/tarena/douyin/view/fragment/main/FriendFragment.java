package com.tarena.douyin.view.fragment.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tarena.douyin.R;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.model.api.APIService;
import com.tarena.douyin.model.bean.RecmmentFriendBean;
import com.tarena.douyin.view.adapter.RecommendFriendAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/11/27 13:31
 * @desc 朋友页面
 */
public class FriendFragment extends BaseFragment {

    @BindView(R.id.rv_recommend_friend)
    RecyclerView rvRecommendFriend;

    private RecommendFriendAdapter adapter;
    private LinearLayoutManager manager;

    private int lastVisibleItemPosition;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void init() {

        manager = new LinearLayoutManager(getActivity());
        rvRecommendFriend.setLayoutManager(manager);
        adapter = new RecommendFriendAdapter();
        rvRecommendFriend.setAdapter(adapter);

        rvRecommendFriend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                    if(lastVisibleItemPosition >= manager.getItemCount() - 2){

//                        System.out.println("====自动加载");
                        getDataFromServer();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        APIService.getInstance().getRecmmendFriend(1, 10, new APIService.APIServiceCallback<List<RecmmentFriendBean>, String>() {
            @Override
            public void onSuccess(List<RecmmentFriendBean> response) {
                adapter.setDatas(response, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }
}