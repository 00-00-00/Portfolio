package com.ground0.portfolio.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

  @OnClick(R.id.f_profiles_github) public void launchGithub() {
    Uri uri = Uri.parse("http://www.github.com/00-00-00");
    startBrowser(uri);
  }

  @OnClick(R.id.f_profiles_linked) public void launchIn() {
    Uri uri = Uri.parse("http://www.linkedin.com/in/00-00-00");
    startBrowser(uri);
  }

  private void startBrowser(Uri uri) {
    try {
      Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
      getActivity().startActivity(launchBrowser);
    } catch (ActivityNotFoundException e) {
      Snackbar.make(mRootView, "Browser missing", Snackbar.LENGTH_SHORT).show();
    }
  }
}
