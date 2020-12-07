// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.personal;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WorkFragment_ViewBinding implements Unbinder {
  private WorkFragment target;

  @UiThread
  public WorkFragment_ViewBinding(WorkFragment target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_work_list, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WorkFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
