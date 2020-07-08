package com.MovieApps.di.component;

import com.MovieApps.di.module.ControllerModule;
import com.MovieApps.view.main.MainController;

import dagger.Subcomponent;

@Subcomponent(modules = ControllerModule.class)
public interface ControllerComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder controllerModule(ControllerModule module);

        ControllerComponent build();
    }

    /* --------------------------------------------------- */
    /* > Inject */
    /* --------------------------------------------------- */

     void inject(MainController controller);
//    void inject(HomeTabController controller);
//    void inject(HomeTabController controller);

}
