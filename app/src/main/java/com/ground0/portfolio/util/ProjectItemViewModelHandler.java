package com.ground0.portfolio.util;

import android.view.View;
import com.ground0.model.Project;

/**
 * Created by zer0 on 14/10/16.
 */
public interface ProjectItemViewModelHandler {

  public void openDetail(Project project, View sharedView);
}
