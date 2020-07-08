package com.MovieApps.di.module;

import android.app.ProgressDialog;
import android.content.Context;

import com.MovieApps.di.scopes.ActivityScope;
import com.MovieApps.view.AppBaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppBaseActivity activity;

    public ActivityModule(AppBaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    Context activityContext() {
        return activity;
    }

    @Provides
    AppBaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    ProgressDialog progressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Please wait…");
        return progressDialog;
    }
}