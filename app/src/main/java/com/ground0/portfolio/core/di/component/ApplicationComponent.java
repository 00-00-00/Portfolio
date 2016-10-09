package com.ground0.portfolio.core.di.component;

import android.content.Context;
import com.ground0.portfolio.activity.HomeActivity;
import com.ground0.portfolio.core.components.BaseActivity;
import com.ground0.portfolio.core.components.BaseApplication;
import com.ground0.portfolio.core.Event;
import com.ground0.portfolio.core.di.module.BaseApplicationModule;
import com.ground0.portfolio.core.di.module.BaseModule;
import dagger.Component;
import javax.inject.Singleton;
import rx.subjects.BehaviorSubject;

/**
 * Created by zer0 on 9/10/16.
 */

@Singleton @Component(modules = { BaseApplicationModule.class, BaseModule.class })
public interface ApplicationComponent {

  void inject(BaseApplication baseApplication);

  void inject(BaseActivity baseActivity);

  void inject(HomeActivity homeActivity);

  //Exposed to sub-graphs.
  Context context();

  // ThreadExecutor threadExecutor();
  // PostExecutionThread postExecutionThread();

  BaseApplication baseApplication();

  BehaviorSubject<Event> systemBehaviorSubject();
}
