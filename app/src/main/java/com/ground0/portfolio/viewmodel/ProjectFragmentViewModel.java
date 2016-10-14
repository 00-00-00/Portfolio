package com.ground0.portfolio.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.ground0.model.Project;
import com.ground0.portfolio.adapter.ProjectRecyclerAdapter;
import com.ground0.portfolio.core.components.BaseFragment;
import com.ground0.portfolio.core.event.ProjectDetailViewEvent;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.ProjectFragment;
import com.ground0.portfolio.util.ProjectItemViewModelHandler;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class ProjectFragmentViewModel extends BaseFragmentViewModel<ProjectFragment>
    implements ProjectItemViewModelHandler {

  ProjectRecyclerAdapter projectRecyclerAdapter;
  List<Project> data = new ArrayList<Project>() {{
    add(new Project());
    add(new Project());
    add(new Project());
  }};

  @Inject public ProjectFragmentViewModel() {
  }

  public ProjectRecyclerAdapter getRecyclerAdapter() {
    if (projectRecyclerAdapter == null) {
      projectRecyclerAdapter = new ProjectRecyclerAdapter(data, this);
    }
    return projectRecyclerAdapter;
  }

  @Override public void openDetail(Project project, View sharedView) {
    if (project != null) {
      getBaseApplication().getAppBehaviourBus().onNext(new ProjectDetailViewEvent(project));
      getActualFragment().startProjectDetailActivity(sharedView);
    }
  }
}
