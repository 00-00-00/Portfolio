package com.ground0.portfolio.core;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zer0 on 9/10/16.
 */

public interface Event<T> {

  T data();

  @EventType int eventType();

  @Retention(RetentionPolicy.SOURCE) @IntDef({}) public @interface EventType {
  }

}
