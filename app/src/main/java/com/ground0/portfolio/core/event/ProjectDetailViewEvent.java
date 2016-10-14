package com.ground0.portfolio.core.event;

import com.ground0.model.Project;
import com.ground0.portfolio.core.Event;

/**
 * Created by zer0 on 14/10/16.
 */
public class ProjectDetailViewEvent implements Event<Project> {

  Project project;

  public ProjectDetailViewEvent(Project project) {
    this.project = project;
  }

  @Override public Project data() {
    return project;
  }

  @Override public int eventType() {
    return OPEN_PROJECT_DETAIL;
  }
}
