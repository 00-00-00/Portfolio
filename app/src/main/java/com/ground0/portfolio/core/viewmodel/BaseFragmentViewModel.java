package com.ground0.portfolio.core.viewmodel;

import com.ground0.portfolio.core.components.BaseApplication;
import com.ground0.portfolio.core.components.BaseFragment;
import java.lang.ref.WeakReference;

/**
 * Created by zer0 on 9/10/16.
 */

public class BaseFragmentViewModel<T extends BaseFragment> {

  WeakReference<T> fragment;

  public void registerFragment(T fragment) {
    this.fragment = new WeakReference<T>(fragment);
  }

  public T getActualFragment() {
    return fragment.get();
  }

  public BaseApplication getBaseApplication() {
    return getActualFragment().getActualActivity().getBaseApplication();
  }
}
