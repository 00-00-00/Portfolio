package com.ground0.portfolio.viewmodel;

import com.ground0.model.Skill;
import com.ground0.portfolio.adapter.SkillRecyclerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.SkillFragment;
import com.ground0.repository.RestApiException;
import com.ground0.repository.repository.UserRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zer0 on 10/10/16.
 */

public class SkillFragmentViewModel extends BaseFragmentViewModel<SkillFragment> {

  @Inject UserRepository userRepository;
  SkillRecyclerAdapter skillRecyclerAdapter;
  List<Object> data = new ArrayList<Object>();
  Comparator<Object> comparator = (lhs, rhs) -> {
    if (lhs.equals(rhs)) return 0;
    if (lhs instanceof String && rhs instanceof String) {
      return compareObject((String) lhs, (String) rhs);
    }
    if (lhs instanceof String && rhs instanceof Skill) {
      return compareObject((String) lhs, (Skill) rhs);
    }
    if (lhs instanceof Skill && rhs instanceof String) {
      return -compareObject((String) rhs, (Skill) lhs);
    }
    if (lhs instanceof Skill && rhs instanceof Skill) {
      return compareObject((Skill) lhs, (Skill) rhs);
    }
    return 0;
  };
  TreeSet<? super Object> treeSet = new TreeSet<>(comparator);

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
    this.treeSet.clear();

    this.treeSet.addAll(data);
    this.treeSet.addAll(getCategories(data));

    this.data.addAll(new ArrayList<>(treeSet));
    this.skillRecyclerAdapter.notifyDataSetChanged();
  }

  private void fetchSkills() {
    getCompositeSubscription().add(userRepository.getSkills()
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

  private int compareObject(Skill skill1, Skill skill2) {
    if (skill1.getCategory().equals(skill2.getCategory())) {
      if (skill1.getProficiencyPercent().equals(skill2.getProficiencyPercent())) {
        return skill1.getName().compareTo(skill2.getName());
      }
      return skill1.getProficiencyPercent().compareTo(skill2.getProficiencyPercent());
    } else {
      return (skill1.getCategory().compareTo(skill2.getCategory()));
    }
  }

  private int compareObject(String string, Skill skill) {
    if (string.equals(skill.getCategory())) {
      return -1;
    } else {
      return string.compareTo(skill.getCategory());
    }
  }

  private int compareObject(String string1, String string2) {
    return string1.compareTo(string2);
  }

  private List<String> getCategories(List<Skill> data) {
    List<String> strings = new ArrayList<>();
    for (Skill skill : data) {
      if (!strings.contains(skill.getCategory())) strings.add(skill.getCategory());
    }
    return strings;
  }
}
