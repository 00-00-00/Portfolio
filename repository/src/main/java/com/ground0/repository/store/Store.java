package com.ground0.repository.store;

import com.ground0.model.Project;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface Store {

  @GET("c06d7127109d390df3ced9727a31b089d3f81ea7/projects.json") Observable<Response<List<Project>>> getProjects();
}
