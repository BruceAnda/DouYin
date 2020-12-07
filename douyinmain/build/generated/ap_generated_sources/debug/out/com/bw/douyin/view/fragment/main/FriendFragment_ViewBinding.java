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

public class FriendFragment_ViewBinding implements Unbinder {
  private FriendFragment target;

  @UiThread
  public FriendFragment_ViewBinding(FriendFragment target, View source) {
    this.target = target;

    target.rvRecommendFriend = Utils.findRequiredViewAsType(source, R.id.rv_recommend_friend, "field 'rvRecommendFriend'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FriendFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvRecommendFriend = null;
  }
}
