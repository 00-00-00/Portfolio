package com.ground0.portfolio.util;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.ground0.portfolio.R;
import rx.Observable;

/**
 * Created by zer0 on 10/10/16.
 */

public class AnimationUtil {

  public static void translateDisappearView(Context context, View view,
      AnimationListener animationListener) {
    Animation animation =
        AnimationUtils.loadAnimation(context, R.anim.translate);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
      }

      @Override public void onAnimationEnd(Animation animation) {
        view.setVisibility(View.GONE);
        view.post(() -> animationListener.callback());
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
    view.startAnimation(animation);
  }

  public static void translateAppearView(Context context, View view,
      AnimationListener animationListener) {
    Animation animation =
        AnimationUtils.loadAnimation(context, android.support.design.R.anim.abc_slide_in_top);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
        view.post(() -> animationListener.callback());
        view.setVisibility(View.VISIBLE);
      }

      @Override public void onAnimationEnd(Animation animation) {
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
    view.startAnimation(animation);
  }

  public interface AnimationListener {
    public void callback();
  }
}
