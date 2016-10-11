package com.ground0.portfolio.viewmodel;

import android.support.v7.widget.RecyclerView;
import com.ground0.portfolio.adapter.ProjectRecyclerAdapter;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class ProjectFragmentViewModel extends BaseFragmentViewModel<BaseFragment> {

  ProjectRecyclerAdapter projectRecyclerAdapter;
  List<Object> data = new ArrayList<Object>() {{
    add(new Object());
    add(new Object());
    add(new Object());
  }};

  @Inject public ProjectFragmentViewModel() {
  }

  public ProjectRecyclerAdapter getRecyclerAdapter() {
    if (projectRecyclerAdapter == null) projectRecyclerAdapter = new ProjectRecyclerAdapter(data);
    return projectRecyclerAdapter;
  }
}
