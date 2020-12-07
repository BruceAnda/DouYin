// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecommendFriendAdapter$ViewHolder_ViewBinding implements Unbinder {
  private RecommendFriendAdapter.ViewHolder target;

  @UiThread
  public RecommendFriendAdapter$ViewHolder_ViewBinding(RecommendFriendAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.sdvAvatar = Utils.findRequiredViewAsType(source, R.id.sdv_avatar, "field 'sdvAvatar'", SimpleDraweeView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvLocation = Utils.findRequiredViewAsType(source, R.id.tv_location, "field 'tvLocation'", TextView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tvMessage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecommendFriendAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sdvAvatar = null;
    target.tvName = null;
    target.tvLocation = null;
    target.tvMessage = null;
  }
}
