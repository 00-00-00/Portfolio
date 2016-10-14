package com.ground0.portfolio.viewmodel;

import android.view.View;
import com.ground0.model.Project;
import com.ground0.portfolio.activity.ProjectDetailActivity;
import com.ground0.portfolio.util.LocalDateUtil;
import com.ground0.portfolio.util.ProjectItemViewModelHandler;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by zer0 on 14/10/16.
 */

public class ProjectItemViewModelFactory {

  ProjectItemViewModelHandler handler;

  public ProjectItemViewModel createViewModel(Project project,
      ProjectItemViewModelHandler handler) {
    this.handler = handler;
    return new ProjectItemViewModel(project);
  }

  public class ProjectItemViewModel {

    public Project project;

    private ProjectItemViewModel(Project project) {
      this.project = project;
    }

    public void openDetail(View view) {
      handler.openDetail(project, view);
    }

    public String getDate() {
      return LocalDateUtil.getDateString(project.getDate());
    }
  }
}
