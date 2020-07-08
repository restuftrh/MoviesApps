package com.MovieApps;

import android.app.Application;
import android.content.Context;
import androidx.annotation.VisibleForTesting;

import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.di.component.ApplicationComponent;

import com.MovieApps.di.component.DaggerApplicationComponent;
import com.MovieApps.di.module.ApplicationModule;
import com.MovieApps.events.AuthenticationErrorEvent;
import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

import static timber.log.Timber.DebugTree;
import static timber.log.Timber.e;
import static timber.log.Timber.plant;

public class CrsApp extends Application {

    private static ApplicationComponent component;
    @Inject EventBus mEventBus;
    @Inject PreferencesHelper mPreferencesHelper;

    public static ApplicationComponent component() {
        return component;
    }

    public static CrsApp get(Context context) {
        return (CrsApp) context.getApplicationContext();
    }

    public static Context appContext() {
        return component.context();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupFabric();
        setupComponent();
        setupTimber();
        mEventBus.register(this);
    }

    private void setupFabric() {
        if (BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            plant(new DebugTree());
        }
    }

    private void setupComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        e("########## onLowMemory ##########");
    }

    @Override
    public void onTerminate() {
        mEventBus.unregister(this);
        super.onTerminate();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AuthenticationErrorEvent event) {
        e("Unauthorized! Redirect to Signin Activity..!.");
        PreferencesHelper.clear();
//        LoginActivity.start(getBaseContext());
    }

    /* --------------------------------------------------- */
    /* > Testing */
    /* --------------------------------------------------- */

    @VisibleForTesting
    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        component = applicationComponent;
    }
}