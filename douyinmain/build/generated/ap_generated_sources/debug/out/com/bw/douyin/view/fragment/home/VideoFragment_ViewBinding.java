// Generated code from Butter Knife. Do not modify!
package com.bw.douyin.view.fragment.home;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bw.douyin.R;
import com.bw.douyin.view.widget.VideoView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoFragment_ViewBinding implements Unbinder {
  private VideoFragment target;

  @UiThread
  public VideoFragment_ViewBinding(VideoFragment target, View source) {
    this.target = target;

    target.videoPlayer = Utils.findRequiredViewAsType(source, R.id.video_player, "field 'videoPlayer'", VideoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoPlayer = null;
  }
}
