package com.ground0.portfolio.core.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ground0.portfolio.core.Event;
import com.ground0.portfolio.core.di.component.ApplicationComponent;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zer0 on 9/10/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

  CompositeSubscription compositeSubscription = new CompositeSubscription();
  PublishSubject<Event> localPublishBus = PublishSubject.create();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeModuleComponent();
    registerActivityWithViewModel();
  }

  protected abstract void registerActivityWithViewModel();

  protected abstract void initializeModuleComponent();

  public CompositeSubscription getCompositeSubscription() {
    return compositeSubscription;
  }

  public BaseApplication getBaseApplication() {
    return (BaseApplication) getApplication();
  }

  public ApplicationComponent getApplicationComponent() {
    return getBaseApplication().getApplicationComponent();
  }

  public PublishSubject<Event> getLocalPublishBus() {
    return localPublishBus;
  }
}
