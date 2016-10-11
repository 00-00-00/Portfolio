package com.ground0.portfolio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.fragment.BioFragment;
import com.ground0.portfolio.fragment.ProjectFragment;
import com.ground0.portfolio.fragment.SkillFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zer0 on 10/10/16.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

  private static final int PAGE_COUNT = 3;
  BioFragment bioFragment = new BioFragment();
  ProjectFragment projectFragment = new ProjectFragment();
  SkillFragment skillFragment = new SkillFragment();

  public HomePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return bioFragment;
      case 1:
        return skillFragment;
      case 2:
        return projectFragment;
    }
    return null;
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }
}
