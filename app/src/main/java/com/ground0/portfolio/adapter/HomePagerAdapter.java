package com.ground0.portfolio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ground0.portfolio.fragment.BioFragment;
import com.ground0.portfolio.fragment.ProjectFragment;

/**
 * Created by zer0 on 10/10/16.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

  private static final int PAGE_COUNT = 2;

  public HomePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new BioFragment();
      case 1:
        return new ProjectFragment();
    }
    return null;
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }
}
