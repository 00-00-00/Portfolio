package com.ground0.portfolio.core.di.component;

/**
 * Created by zer0 on 9/10/16.
 */

import com.ground0.portfolio.core.di.PerActivity;
import com.ground0.portfolio.core.di.module.BaseActivityModule;
import com.ground0.portfolio.core.di.module.BaseApplicationModule;
import com.ground0.portfolio.fragment.BioFragment;
import com.ground0.portfolio.fragment.ProjectFragment;
import com.ground0.portfolio.fragment.SkillFragment;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    BaseActivityModule.class
}) public interface ActivityComponent {

  void inject(BioFragment bioFragment);

  void inject(ProjectFragment projectFragment);

  void inject(SkillFragment skillFragment);
}