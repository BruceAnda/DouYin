// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WorkAdapter$ViewHolder_ViewBinding implements Unbinder {
  private WorkAdapter.ViewHolder target;

  @UiThread
  public WorkAdapter$ViewHolder_ViewBinding(WorkAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ivCover = Utils.findRequiredViewAsType(source, R.id.iv_cover, "field 'ivCover'", ImageView.class);
    target.tvLikeCount = Utils.findRequiredViewAsType(source, R.id.tv_likecount, "field 'tvLikeCount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WorkAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivCover = null;
    target.tvLikeCount = null;
  }
}
