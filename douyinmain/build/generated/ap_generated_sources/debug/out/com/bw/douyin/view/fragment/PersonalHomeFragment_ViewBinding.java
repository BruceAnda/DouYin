// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment;

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

public class PersonalHomeFragment_ViewBinding implements Unbinder {
  private PersonalHomeFragment target;

  @UiThread
  public PersonalHomeFragment_ViewBinding(PersonalHomeFragment target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PersonalHomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
  }
}
