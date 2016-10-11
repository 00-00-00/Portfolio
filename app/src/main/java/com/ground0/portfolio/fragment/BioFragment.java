package com.ground0.portfolio.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.databinding.FragmentBioBinding;
import com.ground0.portfolio.util.AnimationUtil;
import com.ground0.portfolio.util.BackPressHandler;
import com.ground0.portfolio.util.Constants;
import com.ground0.portfolio.viewmodel.BioFragmentViewModel;
import com.squareup.picasso.Picasso;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import com.ground0.portfolio.R;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioFragment extends BaseFragment<HomeActivity> implements BackPressHandler {

  @Inject BioFragmentViewModel viewModel;

  @Retention(RetentionPolicy.SOURCE) @IntDef({ STATE_1, STATE_2 })
  public @interface FragmentStates {
  }

  public static final int STATE_1 = 0;
  public static final int STATE_2 = 1;
  public static final String FRAGMENT_STATE_KEY = "biofrag_state_key";
  @BindView(R.id.f_bio_image) ImageView imageView;
  View mRootView;
  FragmentBioBinding fragmentBioBinding;
  @FragmentStates int fragmentState;

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
    fragmentBioBinding = DataBindingUtil.bind(mRootView);
    initUI();
    return mRootView;
  }

  @Override public boolean onBackPressed() {
    if (fragmentState == STATE_2) {
      fragmentState = STATE_1;
      invalidateFragmentState();
      return true;
    } else {
      return false;
    }
  }

  @OnClick(R.id.f_bio_next) public void nextScreen() {
    fragmentState = STATE_2;
    invalidateFragmentState();
  }

  @OnClick(R.id.f_bio_title_1) public void title1Click() {
    setActiveDescription(R.id.f_bio_title_1);
  }

  @OnClick(R.id.f_bio_title_2) public void title2Click() {
    setActiveDescription(R.id.f_bio_title_2);
  }

  @OnClick(R.id.f_bio_title_3) public void title3Click() {
    setActiveDescription(R.id.f_bio_title_3);
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
    Picasso.with(getContext()).load(Constants.Image_URL).into(imageView);
    fragmentState = STATE_1;
  }

  private void invalidateFragmentState() {
    switch (fragmentState) {
      case STATE_1:
        AnimationUtil.translateAppearView(getContext(), fragmentBioBinding.fBioContainer1, () -> {
          fragmentBioBinding.fBioContainer2.setVisibility(View.GONE);
        });
        break;
      case STATE_2:
        AnimationUtil.translateDisappearView(getContext(), fragmentBioBinding.fBioContainer1,
            () -> {
              fragmentBioBinding.fBioContainer2.setVisibility(View.VISIBLE);
            });
    }
  }

  private void setActiveDescription(int viewId) {
    switch (viewId) {
      case R.id.f_bio_title_1:
        toggleVisibility(fragmentBioBinding.fBioDesc1);
        fragmentBioBinding.fBioDesc2.setVisibility(View.GONE);
        fragmentBioBinding.fBioDesc3.setVisibility(View.GONE);
        break;
      case R.id.f_bio_title_2:
        fragmentBioBinding.fBioDesc1.setVisibility(View.GONE);
        toggleVisibility(fragmentBioBinding.fBioDesc2);
        fragmentBioBinding.fBioDesc3.setVisibility(View.GONE);
        break;
      case R.id.f_bio_title_3:
        fragmentBioBinding.fBioDesc1.setVisibility(View.GONE);
        fragmentBioBinding.fBioDesc2.setVisibility(View.GONE);
        toggleVisibility(fragmentBioBinding.fBioDesc3);
        break;
    }
  }

  private void toggleVisibility(View view) {
    if (view == null) return;
    view.setVisibility(view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
  }
}
