// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding implements Unbinder {
  private MainFragment target;

  @UiThread
  public MainFragment_ViewBinding(MainFragment target, View source) {
    this.target = target;

    target.mBottomTab = Utils.findRequiredViewAsType(source, R.id.tab_bottom, "field 'mBottomTab'", TabLayout.class);
    target.ivRecord = Utils.findRequiredViewAsType(source, R.id.iv_record, "field 'ivRecord'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBottomTab = null;
    target.ivRecord = null;
  }
}
