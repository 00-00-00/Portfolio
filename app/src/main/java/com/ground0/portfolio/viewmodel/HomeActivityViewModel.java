package com.ground0.portfolio.viewmodel;

import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.adapter.HomePagerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseActivityViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 9/10/16.
 */

public class HomeActivityViewModel extends BaseActivityViewModel<HomeActivity> {

  HomePagerAdapter homePagerAdapter;

  @Inject public HomeActivityViewModel() {
  }

  public HomePagerAdapter getPagerAdapter() {
    if (homePagerAdapter == null) {
      homePagerAdapter = new HomePagerAdapter(getActualActivity().getSupportFragmentManager());
    }
    return homePagerAdapter;
  }
}
