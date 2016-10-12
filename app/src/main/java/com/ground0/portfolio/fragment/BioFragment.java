package com.ground0.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.adapter.BioPagerAdapter;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.util.BackPressHandler;
import com.ground0.portfolio.viewmodel.BioFragmentViewModel;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioFragment extends BaseFragment<HomeActivity> implements BackPressHandler {

  @Inject BioFragmentViewModel viewModel;
  @BindView(R.id.f_bio_viewpager) VerticalViewPager viewPager;

  public static final String FRAGMENT_STATE_KEY = "biofrag_state_key";
  View mRootView;
  int fragmentState;

  @Override protected void registerFragmentWithViewModel() {
    viewModel.registerFragment(this);
  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_bio, container, false);
    ButterKnife.bind(this, mRootView);
    initUI();
    return mRootView;
  }

  @Override public boolean onBackPressed() {
    //if (fragmentState == STATE_2) {
    //  fragmentState = STATE_1;
    //  invalidateFragmentState();
    //  return true;
    //} else {
    //  return false;
    //}
    return false;
  }

  @Override public void onSaveInstanceState(Bundle outState) {

    outState.putInt(FRAGMENT_STATE_KEY, fragmentState);
    super.onSaveInstanceState(outState);
  }

  @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    if (savedInstanceState == null) return;
    //noinspection WrongConstant
    fragmentState = savedInstanceState.getInt(FRAGMENT_STATE_KEY);
  }

  private void initUI() {
    viewPager.setAdapter(new BioPagerAdapter(getChildFragmentManager()));
  }
}
