// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.home;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecommendFragment_ViewBinding implements Unbinder {
  private RecommendFragment target;

  @UiThread
  public RecommendFragment_ViewBinding(RecommendFragment target, View source) {
    this.target = target;

    target.videoViewPager = Utils.findRequiredViewAsType(source, R.id.vp_video, "field 'videoViewPager'", ViewPager2.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RecommendFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoViewPager = null;
  }
}
