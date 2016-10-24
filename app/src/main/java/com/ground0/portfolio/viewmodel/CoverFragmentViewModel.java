package com.ground0.portfolio.viewmodel;

import com.ground0.model.Self;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.CoverFragment;
import com.ground0.repository.RestApiException;
import com.ground0.repository.repository.UserRepository;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zer0 on 25/10/16.
 */

public class CoverFragmentViewModel extends BaseFragmentViewModel<CoverFragment> {

  @Inject UserRepository userRepository;
  Self self;

  @Inject public CoverFragmentViewModel() {
  }

  @Override public void afterRegister() {
    super.afterRegister();
    getSelf();
  }

  private void getSelf() {
    getCompositeSubscription().add(userRepository.getProjects()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriptionBuilder.builder().onNext(val -> {
          self = (Self) val;
          getActualFragment().selfRefreshed(self);
        }).onError(e -> {
          String errorText = "Something went wrong";
          if (e instanceof RestApiException) {
            RestApiException restApiException = (RestApiException) e;
            if (restApiException.getError() != null) {
              errorText = StringUtils.isNotEmpty(restApiException.getError().getMessage())
                  ? restApiException.getError().getMessage() : errorText;
            }
          }
        }).setFinishOnComplete().build()));
  }
}
