package com.ground0.portfolio.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.core.components.BaseActivity;
import com.ground0.portfolio.databinding.ActivityProjectDetailBinding;
import com.ground0.portfolio.viewmodel.ProjectDetailActivityViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 14/10/16.
 */

public class ProjectDetailActivity extends BaseActivity {

  @Inject ProjectDetailActivityViewModel viewModel;
  @BindView(R.id.a_project_detail_container) View containerView;
  ActivityProjectDetailBinding projectDetailBinding;

  @Override protected void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    projectDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_project_detail);
    ButterKnife.bind(this);
    initUI();
    viewModel.initSubscriptions();
  }

  private void initUI() {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      containerView.setTransitionName(getString(R.string.activity_image_trans));
    }
    getSupportActionBar().setTitle("Project Name");
  }
}
