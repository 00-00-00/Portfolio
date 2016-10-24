package com.ground0.portfolio.viewmodel;

import android.view.View;
import com.ground0.model.Project;
import com.ground0.portfolio.adapter.ProjectRecyclerAdapter;
import com.ground0.portfolio.core.event.ProjectDetailViewEvent;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.ProjectFragment;
import com.ground0.portfolio.util.ProjectItemViewModelHandler;
import com.ground0.repository.RestApiException;
import com.ground0.repository.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zer0 on 10/10/16.
 */

public class ProjectFragmentViewModel extends BaseFragmentViewModel<ProjectFragment>
    implements ProjectItemViewModelHandler {

  @Inject UserRepository userRepository;
  ProjectRecyclerAdapter projectRecyclerAdapter;
  List<Project> data = new ArrayList<Project>();

  @Inject public ProjectFragmentViewModel() {
  }

  public ProjectRecyclerAdapter getRecyclerAdapter() {
    if (projectRecyclerAdapter == null) {
      projectRecyclerAdapter = new ProjectRecyclerAdapter(data, this);
    }
    return projectRecyclerAdapter;
  }

  public void setData(List<Project> data) {
    if (data == null) return;
    this.data.clear();
    this.data.addAll(data);
    projectRecyclerAdapter.notifyDataSetChanged();
  }

  @Override public void afterRegister() {
    super.afterRegister();
    fetchProjects();
  }

  @Override public void openDetail(Project project, View sharedView) {
    if (project != null) {
      getBaseApplication().getAppBehaviourBus().onNext(new ProjectDetailViewEvent(project));
      getActualFragment().startProjectDetailActivity(sharedView);
    }
  }

  private void fetchProjects() {
    getActualFragment().initDataLoad();
    getCompositeSubscription().add(userRepository.getProjects()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriptionBuilder.builder().onNext(val -> {
          setData((List<Project>) val);
          getActualFragment().dataLoadComplete();
        }).onError(e -> {
          String errorText = "Something went wrong";
          if (e instanceof RestApiException) {
            RestApiException restApiException = (RestApiException) e;
            if (restApiException.getError() != null) {
              errorText = StringUtils.isNotEmpty(restApiException.getError().getMessage())
                  ? restApiException.getError().getMessage() : errorText;
            }
          }
          getActualFragment().dataLoadFailed(errorText);
        }).setFinishOnComplete().build()));
  }

  public void reloadData() {
    fetchProjects();
  }

  public List<Project> getData() {
    return data;
  }
}
