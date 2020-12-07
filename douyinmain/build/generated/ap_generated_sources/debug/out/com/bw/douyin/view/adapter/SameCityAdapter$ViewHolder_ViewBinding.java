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

public class SameCityAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SameCityAdapter.ViewHolder target;

  @UiThread
  public SameCityAdapter$ViewHolder_ViewBinding(SameCityAdapter.ViewHolder target, View source) {
    this.target = target;

    target.sdvImage = Utils.findRequiredViewAsType(source, R.id.sdv_image, "field 'sdvImage'", SimpleDraweeView.class);
    target.sdvAvatar = Utils.findRequiredViewAsType(source, R.id.sdv_avatar, "field 'sdvAvatar'", SimpleDraweeView.class);
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tv_distance, "field 'tvDistance'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SameCityAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sdvImage = null;
    target.sdvAvatar = null;
    target.tvDistance = null;
  }
}
