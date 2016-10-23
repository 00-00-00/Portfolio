package com.ground0.portfolio.viewmodel;

import com.ground0.model.Skill;
import com.ground0.portfolio.adapter.SkillRecyclerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.SkillFragment;
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

public class SkillFragmentViewModel extends BaseFragmentViewModel<SkillFragment> {

  @Inject UserRepository userRepository;
  SkillRecyclerAdapter skillRecyclerAdapter;
  List<Skill> data = new ArrayList<Skill>();

  @Inject public SkillFragmentViewModel() {
  }

  public SkillRecyclerAdapter getRecyclerAdapter() {
    if (skillRecyclerAdapter == null) skillRecyclerAdapter = new SkillRecyclerAdapter(data);
    return skillRecyclerAdapter;
  }

  @Override public void afterRegister() {
    super.afterRegister();
    fetchSkills();
  }

  public void setData(List<Skill> data) {
    if (data == null) return;
    this.data.clear();
    this.data.addAll(data);
    this.skillRecyclerAdapter.notifyDataSetChanged();
  }

  private void fetchSkills() {
    getCompositeSubscription().add(userRepository.getProjects()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriptionBuilder.builder().onNext(val -> {
          setData((List<Skill>) val);
        }).onError(e -> {
          String errorText = "Something went wrong";
          if (e instanceof RestApiException) {
            RestApiException restApiException = (RestApiException) e;
            if (restApiException.getError() != null) {
              errorText = StringUtils.isNotEmpty(restApiException.getError().getMessage())
                  ? restApiException.getError().getMessage() : errorText;
            }
          }
          getActualFragment().displayError(errorText);
        }).setFinishOnComplete().build()));
  }
}
