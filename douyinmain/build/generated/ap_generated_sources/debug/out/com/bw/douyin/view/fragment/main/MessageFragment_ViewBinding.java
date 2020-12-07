// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.main;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageFragment_ViewBinding implements Unbinder {
  private MessageFragment target;

  @UiThread
  public MessageFragment_ViewBinding(MessageFragment target, View source) {
    this.target = target;

    target.rvFriendMessageList = Utils.findRequiredViewAsType(source, R.id.rv_friend_message_list, "field 'rvFriendMessageList'", RecyclerView.class);
    target.rvRecommendFriend = Utils.findRequiredViewAsType(source, R.id.rv_recommend_friend, "field 'rvRecommendFriend'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvFriendMessageList = null;
    target.rvRecommendFriend = null;
  }
}
