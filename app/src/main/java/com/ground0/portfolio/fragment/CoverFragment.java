package com.ground0.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.util.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by zer0 on 12/10/16.
 */

public class CoverFragment extends BaseFragment<HomeActivity> {

  @BindView(R.id.f_bio_image) ImageView imageView;
  View mRootView;

  @Override protected void registerFragmentWithViewModel() {

  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_cover, container, false);
    ButterKnife.bind(this, mRootView);
    initUI();
    return mRootView;
  }

  private void initUI() {
    Picasso.with(getContext()).load(Constants.Image_URL).into(imageView);
  }
}
