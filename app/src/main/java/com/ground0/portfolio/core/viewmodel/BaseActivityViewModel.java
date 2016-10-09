package com.ground0.portfolio.core.viewmodel;

import com.ground0.portfolio.core.components.BaseActivity;
import java.lang.ref.WeakReference;

/**
 * Created by zer0 on 9/10/16.
 */

public abstract class BaseActivityViewModel<T extends BaseActivity> implements ViewModel {

  WeakReference<T> activity;

  public void registerActivity(T activity) {
    this.activity = new WeakReference<T>(activity);
  }

  public T getActualActivity() {
    return (T) activity.get();
  }
}
