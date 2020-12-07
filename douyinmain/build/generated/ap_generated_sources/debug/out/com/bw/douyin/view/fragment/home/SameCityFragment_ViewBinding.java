// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.home;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SameCityFragment_ViewBinding implements Unbinder {
  private SameCityFragment target;

  @UiThread
  public SameCityFragment_ViewBinding(SameCityFragment target, View source) {
    this.target = target;

    target.rvSameCityList = Utils.findRequiredViewAsType(source, R.id.rv_same_city_list, "field 'rvSameCityList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SameCityFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvSameCityList = null;
  }
}
