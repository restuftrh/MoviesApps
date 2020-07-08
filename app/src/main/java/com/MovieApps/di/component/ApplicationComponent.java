package com.MovieApps.di.component;

import android.content.Context;

import com.MovieApps.CrsApp;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.data.remote.UnauthorisedInterceptor;
import com.MovieApps.di.module.ApplicationModule;
import com.MovieApps.di.module.NetworkModule;
import com.MovieApps.di.qualifiers.AppContext;
import com.MovieApps.view.fragment.HomeFragment;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public  interface ApplicationComponent {

    void inject(CrsApp app);
    void inject(UnauthorisedInterceptor interceptor);




    /* --------------------------------------------------- */
    /* > Public Dependencies */
    /* --------------------------------------------------- */
    void inject(HomeFragment fragment);


    @AppContext
    Context context();
    ApiService apiService();
    EventBus eventBus();
    PreferencesHelper prefsHelper();
}