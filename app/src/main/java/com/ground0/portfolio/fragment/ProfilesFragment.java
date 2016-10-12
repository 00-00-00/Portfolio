package com.ground0.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;

/**
 * Created by zer0 on 12/10/16.
 */

public class ProfilesFragment extends BaseFragment<HomeActivity> {

  View mRootView;

  @Override protected void registerFragmentWithViewModel() {

  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_profiles, container, false);
    ButterKnife.bind(this, mRootView);
    return mRootView;
  }
}
