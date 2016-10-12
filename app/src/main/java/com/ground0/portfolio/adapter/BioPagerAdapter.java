package com.ground0.portfolio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ground0.portfolio.fragment.CoverFragment;
import com.ground0.portfolio.fragment.IntroFragment;
import com.ground0.portfolio.fragment.ProfilesFragment;

/**
 * Created by zer0 on 12/10/16.
 */

public class BioPagerAdapter extends FragmentStatePagerAdapter {

  private static final int PAGE_COUNT = 3;
  private CoverFragment coverFragment = new CoverFragment();
  private IntroFragment introFragment = new IntroFragment();
  private ProfilesFragment profilesFragment = new ProfilesFragment();

  public BioPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return coverFragment;
      case 1:
        return introFragment;
      case 2:
        return profilesFragment;
      default:
        return coverFragment;
    }
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }
}
