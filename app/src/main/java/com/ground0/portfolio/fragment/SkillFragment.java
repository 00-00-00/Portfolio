package com.ground0.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.viewmodel.SkillFragmentViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class SkillFragment extends BaseFragment<HomeActivity> {

  @Inject SkillFragmentViewModel viewModel;
  View mRootView;
  @BindView(R.id.f_skill_recycler) RecyclerView recyclerView;
  @BindView(R.id.f_skill_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.f_skill_empty_view) View view;

  @Override protected void registerFragmentWithViewModel() {
    viewModel.registerFragment(this);
  }

  @Override protected void injectDependencies() {
    getActivityComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_skill, container, false);
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
    view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
  }

  public void dataLoadFailed(String errorText) {
    dataLoadComplete();
    getActualActivity().showSnackBar(errorText, getString(R.string.retry),
        view -> viewModel.reloadData());
  }
}
