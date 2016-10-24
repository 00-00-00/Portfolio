package com.ground0.repository;

import com.ground0.model.Project;
import com.ground0.model.Self;
import com.ground0.model.Skill;
import com.ground0.repository.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created by zer0 on 20/10/16.
 */
@Singleton public class UserRepositoryImpl implements UserRepository {

  @Inject public UserRepositoryImpl() {
  }

  @Inject @Named("cloudStore") UserRepository cloudDataStore;

  @Override public Observable<List<Project>> getProjects() {
    return cloudDataStore.getProjects();
  }

  @Override public Observable<List<Skill>> getSkills() {
    return cloudDataStore.getSkills();
  }

  @Override public Observable<Self> getSelf() {
    return cloudDataStore.getSelf();
  }
}
