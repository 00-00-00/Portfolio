package com.ground0.repository.repository;

import com.ground0.model.Project;
import com.ground0.model.Skill;
import java.util.List;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface UserRepository {
  Observable<List<Project>> getProjects();
  Observable<List<Skill>> getSkills();
}
