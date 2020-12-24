package com.tarena.douyin.view.fragment.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tarena.douyin.R;
import com.tarena.douyin.base.BaseFragment;
import com.tarena.douyin.model.api.APIService;
import com.tarena.douyin.model.bean.PersonMessageBean;
import com.tarena.douyin.model.bean.RecmmentFriendBean;
import com.tarena.douyin.view.adapter.PersonMessageListAdapter;
import com.tarena.douyin.view.adapter.RecommendFriendAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @version 1.0
 * @auther Dell Precision
 * @date 2020/11/27 13:31
 * @desc 消息页面
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.rv_friend_message_list)
    RecyclerView rvFriendMessageList;
    @BindView(R.id.rv_recommend_friend)
    RecyclerView rvRecommendFriend;

    private PersonMessageListAdapter personMessageListAdapter;

    private RecommendFriendAdapter adapter;
    private LinearLayoutManager manager;

    private int lastVisibleItemPosition;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init() {

        personMessageListAdapter = new PersonMessageListAdapter();
        rvFriendMessageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFriendMessageList.setAdapter(personMessageListAdapter);

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

//                      System.out.println("====自动加载");
                        getRecommendFriendDataFromServer();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        getPersonMessageDataFromServer();
        getRecommendFriendDataFromServer();
    }

    private void getPersonMessageDataFromServer() {
        APIService.getInstance().getPersonMessageList(1, 10, new APIService.APIServiceCallback<List<PersonMessageBean>, String>() {
            @Override
            public void onSuccess(List<PersonMessageBean> response) {
                personMessageListAdapter.setDatas(response, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

    private void getRecommendFriendDataFromServer() {
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