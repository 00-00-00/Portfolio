package com.ground0.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ground0.portfolio.R;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;

/**
 * Created by zer0 on 12/10/16.
 */

public class IntroFragment extends BaseFragment<HomeActivity> {
  
  View mRootView;
  @BindView(R.id.f_intro_desc_1) View fBioDesc1;
  @BindView(R.id.f_intro_desc_2) View fBioDesc2;
  @BindView(R.id.f_intro_desc_3) View fBioDesc3;
  
  @Override protected void registerFragmentWithViewModel() {

  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_intro, container, false);
    ButterKnife.bind(this, mRootView);
    return mRootView;
  }

  @OnClick(R.id.f_intro_title_1) public void title1Click() {
    setActiveDescription(R.id.f_intro_title_1);
  }

  @OnClick(R.id.f_intro_title_2) public void title2Click() {
    setActiveDescription(R.id.f_intro_title_2);
  }

  @OnClick(R.id.f_intro_title_3) public void title3Click() {
    setActiveDescription(R.id.f_intro_title_3);
  }

  private void setActiveDescription(int viewId) {
    switch (viewId) {
      case R.id.f_intro_title_1:
        toggleVisibility(fBioDesc1);
        fBioDesc2.setVisibility(View.GONE);
        fBioDesc3.setVisibility(View.GONE);
        break;
      case R.id.f_intro_title_2:
        fBioDesc1.setVisibility(View.GONE);
        toggleVisibility(fBioDesc2);
        fBioDesc3.setVisibility(View.GONE);
        break;
      case R.id.f_intro_title_3:
        fBioDesc1.setVisibility(View.GONE);
        fBioDesc2.setVisibility(View.GONE);
        toggleVisibility(fBioDesc3);
        break;
    }
  }

  private void toggleVisibility(View view) {
    if (view == null) return;
    view.setVisibility(view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
  }
}
