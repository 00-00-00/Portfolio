package com.ground0.portfolio.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.databinding.FragmentBioBinding;
import com.ground0.portfolio.viewmodel.BioFragmentViewModel;
import javax.inject.Inject;
import com.ground0.portfolio.R;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioFragment extends BaseFragment<HomeActivity> {

  @Inject BioFragmentViewModel viewModel;
  View mRootView;
  FragmentBioBinding fragmentBioBinding;

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
    return mRootView;
  }
}
