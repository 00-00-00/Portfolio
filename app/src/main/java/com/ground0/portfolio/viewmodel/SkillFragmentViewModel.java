package com.ground0.portfolio.viewmodel;

import com.ground0.model.Skill;
import com.ground0.portfolio.adapter.SkillRecyclerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.SkillFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class SkillFragmentViewModel extends BaseFragmentViewModel<SkillFragment> {

  SkillRecyclerAdapter skillRecyclerAdapter;
  List<Skill> data = new ArrayList<Skill>() {{
    add(new Skill());
    add(new Skill());
  }};

  @Inject public SkillFragmentViewModel() {
  }

  public SkillRecyclerAdapter getRecyclerAdapter() {
    if (skillRecyclerAdapter == null) skillRecyclerAdapter = new SkillRecyclerAdapter(data);
    return skillRecyclerAdapter;
  }
}
