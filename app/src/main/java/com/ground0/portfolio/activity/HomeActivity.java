package com.ground0.portfolio.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.ground0.portfolio.R;
import com.ground0.portfolio.core.components.BaseActivity;
import com.ground0.portfolio.databinding.ActivityBaseBinding;
import com.ground0.portfolio.viewmodel.HomeActivityViewModel;
import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

  @Inject HomeActivityViewModel viewModel;
  ActivityBaseBinding activityBaseBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
    initToolBar();
    initViewPager();
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  @Override public void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  private void initToolBar() {
    setSupportActionBar(activityBaseBinding.aBaseToolbar);
  }

  private void initViewPager() {
    TabLayout tabLayout = activityBaseBinding.aBaseTablayout;
    ViewPager viewPager = activityBaseBinding.aBaseViewPager;

    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_outline_white_24dp));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_devices_white_24dp));
    viewPager.setAdapter(viewModel.getPagerAdapter());
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        switch (tab.getPosition())
        {
          case 0 :
            getSupportActionBar().setTitle("Bio");
            break;
          case 1 :
            getSupportActionBar().setTitle("Projects");
            break;
        }
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }
}
