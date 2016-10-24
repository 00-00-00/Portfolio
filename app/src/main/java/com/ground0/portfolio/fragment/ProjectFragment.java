package com.ground0.portfolio.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.activity.ProjectDetailActivity;
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
  @BindView(R.id.f_projects_empty_view) View emptyView;
  @BindView(R.id.f_projects_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
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
    swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.key_lime),
        ContextCompat.getColor(getContext(), R.color.orange_soda),
        ContextCompat.getColor(getContext(), R.color.mellow_apricot),
        ContextCompat.getColor(getContext(), R.color.munsel_green));
    swipeRefreshLayout.setOnRefreshListener(() -> {
      viewModel.reloadData();
    });
  }

  public void startProjectDetailActivity(View sharedViews) {

    Intent intent = new Intent(getActualActivity(), ProjectDetailActivity.class);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      ActivityOptionsCompat options = ActivityOptionsCompat.
          makeSceneTransitionAnimation(getActualActivity(), sharedViews,
              getString(R.string.activity_image_trans));
      startActivity(intent, options.toBundle());
    } else {
      startActivity(intent);
    }
  }

  public void initDataLoad() {
    swipeRefreshLayout.setRefreshing(true);
  }

  public void dataLoadComplete() {
    swipeRefreshLayout.post(() -> {
      swipeRefreshLayout.setRefreshing(false);
    });
    if (viewModel.getData().size() == 0) {
      toggleEmptyView(true);
    } else {
      toggleEmptyView(false);
    }
  }

  public void toggleEmptyView(boolean show) {
    emptyView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
  }

  public void dataLoadFailed(String errorText) {
    dataLoadComplete();
    getActualActivity().showSnackBar(errorText, getString(R.string.retry),
        view -> viewModel.reloadData());
  }
}
