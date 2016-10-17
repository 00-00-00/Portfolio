package com.ground0.portfolio.core.di.module;

import com.ground0.portfolio.core.components.BaseApplication;
import com.ground0.portfolio.core.rx.ISubscriptionBuilder;
import com.ground0.portfolio.core.rx.SubscriptionBuilder;
import com.ground0.repository.repository.UserRepository;
import com.ground0.repository.store.CloudDataStore;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
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

  @Provides @Singleton ISubscriptionBuilder provideDefaultActivitySubscription(
      SubscriptionBuilder subscriptionBuilder) {
    return subscriptionBuilder;
  }

  @Provides @Singleton @Named("cloudStore") UserRepository provideCloudDataStore(
      CloudDataStore store) {
    return store;
  }
}
