package com.MovieApps.di.module;

import com.bluelinelabs.conductor.Controller;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {

    private final Controller controller;

    public ControllerModule(Controller controller) {
        this.controller = controller;
    }

    @Provides
    Controller provideController() {
        return controller;
    }
}
