package com.ground0.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zer0 on 17/10/16.
 */
public class HttpResponseStatusOperator<T> implements Observable.Operator<T, Response<T>> {

  ObjectMapper objectMapper;

  public HttpResponseStatusOperator(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override public Subscriber<? super Response<T>> call(final Subscriber<? super T> subscriber) {
    return new Subscriber<Response<T>>() {
      @Override public void onCompleted() {
        if (!subscriber.isUnsubscribed()) subscriber.onCompleted();
      }

      @Override public void onError(Throwable e) {
        if (subscriber.isUnsubscribed()) return;
        subscriber.onError(e);
        subscriber.onCompleted();
      }

      @Override public void onNext(Response<T> response) {
        if (subscriber.isUnsubscribed())return;
        if (response.isSuccessful()) {
          subscriber.onNext(response.body());
        }
        else
        {
          try {
            subscriber.onError(new RestApiException(response.code(), response.errorBody().string(), objectMapper));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    };
  }
}
