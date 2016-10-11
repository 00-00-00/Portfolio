package com.ground0.portfolio.viewmodel;

import com.ground0.portfolio.adapter.SkillRecyclerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.BioFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioFragmentViewModel extends BaseFragmentViewModel<BioFragment> {

  List<Object> data = new ArrayList<Object>() {{
    add(new Object());
  }};

  @Inject public BioFragmentViewModel() {

  }
}
