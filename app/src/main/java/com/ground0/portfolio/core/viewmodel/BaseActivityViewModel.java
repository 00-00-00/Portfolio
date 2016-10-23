package com.ground0.portfolio.core.viewmodel;

import com.ground0.portfolio.core.components.BaseActivity;
import com.ground0.portfolio.core.components.BaseApplication;
import com.ground0.portfolio.core.rx.SubscriptionBuilder;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zer0 on 9/10/16.
 */

public abstract class BaseActivityViewModel<T extends BaseActivity> implements ViewModel {

  WeakReference<T> activity;
  @Inject SubscriptionBuilder subscriptionBuilder;

  public void registerActivity(T activity) {
    this.activity = new WeakReference<T>(activity);
    afterRegister();
  }

  public T getActualActivity() {
    return (T) activity.get();
  }

  public BaseApplication getBaseApplication() {
    return getActualActivity().getBaseApplication();
  }

  public SubscriptionBuilder getSubscriptionBuilder() {
    return subscriptionBuilder;
  }

  public CompositeSubscription getCompositeSubscription() {
    return getActualActivity().getCompositeSubscription();
  }

  public void setSubscriptionBuilder(SubscriptionBuilder subscriptionBuilder) {
    this.subscriptionBuilder = subscriptionBuilder;
  }

  public void afterRegister() {

  }
}
