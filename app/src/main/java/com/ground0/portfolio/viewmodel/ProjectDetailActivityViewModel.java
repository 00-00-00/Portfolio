package com.ground0.portfolio.viewmodel;

import com.ground0.model.Project;
import com.ground0.portfolio.activity.ProjectDetailActivity;
import com.ground0.portfolio.core.event.ProjectDetailViewEvent;
import com.ground0.portfolio.core.viewmodel.BaseActivityViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 14/10/16.
 */

public class ProjectDetailActivityViewModel extends BaseActivityViewModel<ProjectDetailActivity> {

  Project project;

  @Inject public ProjectDetailActivityViewModel() {
  }

  public void initSubscriptions() {
    getBaseApplication().getAppBehaviourBus()
        .filter(event -> event instanceof ProjectDetailViewEvent)
        .subscribe(event -> {
          this.project = (Project) event.data();
        });
  }

  public Project getProject() {
    return project;
  }
}
