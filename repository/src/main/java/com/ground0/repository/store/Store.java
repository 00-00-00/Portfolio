package com.ground0.repository.store;

import com.ground0.model.Project;
import com.ground0.model.Self;
import com.ground0.model.Skill;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface Store {

  @GET("projects.json") Observable<Response<List<Project>>> getProjects();

  @GET("skills.json") Observable<Response<List<Skill>>> getSkills();

  @GET("self.json") Observable<Response<Self>> getSelf();
}
