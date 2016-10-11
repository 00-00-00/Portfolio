package com.ground0.portfolio.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.databinding.FragmentProjectsBinding;
import com.ground0.portfolio.viewmodel.ProjectFragmentViewModel;
import javax.inject.Inject;
import com.ground0.portfolio.R;

/**
 * Created by zer0 on 10/10/16.
 */

public class ProjectFragment extends BaseFragment<HomeActivity> {

  @Inject ProjectFragmentViewModel viewModel;
  View mRootView;
  @BindView(R.id.f_projects_recycler) RecyclerView recyclerView;
  FragmentProjectsBinding fragmentProjectsBinding;

  @Override protected void registerFragmentWithViewModel() {
    viewModel.registerFragment(this);
  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_projects, container, false);
    fragmentProjectsBinding = DataBindingUtil.bind(mRootView);
    ButterKnife.bind(this, mRootView);
    initRecyclerView();
    return mRootView;
  }

  private void initRecyclerView() {
    recyclerView.setAdapter(viewModel.getRecyclerAdapter());
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }
}
