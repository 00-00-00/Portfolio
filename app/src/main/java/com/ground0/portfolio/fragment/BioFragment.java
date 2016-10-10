package com.ground0.portfolio.fragment;

import android.databinding.DataBindingUtil;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import rx.Observable;
import rx.Subscription;

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
}
