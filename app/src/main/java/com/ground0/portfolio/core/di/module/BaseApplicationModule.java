package com.ground0.portfolio.core.di.module;

import com.ground0.portfolio.core.components.BaseApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by zer0 on 9/10/16.
 */

@Module public class BaseApplicationModule {
  private final BaseApplication application;

  public BaseApplicationModule(BaseApplication application) {
    this.application = application;
  }

  @Provides @Singleton BaseApplication providePatientApplication() {
    return this.application;
  }
}
