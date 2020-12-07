// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.main;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.mTabTitle = Utils.findRequiredViewAsType(source, R.id.tab_title, "field 'mTabTitle'", TabLayout.class);
    target.mVpContent = Utils.findRequiredViewAsType(source, R.id.vp_content, "field 'mVpContent'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTabTitle = null;
    target.mVpContent = null;
  }
}
