package com.MovieApps.di.component;

import com.MovieApps.di.module.ActivityModule;
import com.MovieApps.di.scopes.ActivityScope;
import com.MovieApps.view.AppBaseActivity;
import com.MovieApps.view.fragment.HomeFragment;
import com.MovieApps.view.main.MainActivity;
import com.MovieApps.view.main.MainController;
import com.MovieApps.view.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent extends ApplicationComponent {

    void inject(AppBaseActivity baseActivity);
    void inject(MainActivity activity);
    void inject(SplashActivity activity);


    /* --------------------------------------------------- */
    /* > Controller */
    /* --------------------------------------------------- */

    void inject (MainController controller);

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    /* ---------------------------------------------------- */
    /* > Fragment */
    /* ---------------------------------------------------- */

    void inject(HomeFragment fragment);

    ControllerComponent.Builder controllerComponent();
}