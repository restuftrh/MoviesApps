package com.MovieApps.di.module;

import android.content.Context;

import com.MovieApps.CrsApp;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.di.qualifiers.AppContext;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final CrsApp application;

    public ApplicationModule(CrsApp baseApplication) {
        this.application = baseApplication;
    }

    @Provides
    @Singleton
    @AppContext
    Context provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
    CrsApp provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    PreferencesHelper prefsHelper() {
        return new PreferencesHelper(application);
    }


}