package com.MovieApps.view.fragment.presenter;

import android.content.Context;

import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.view.commons.AbsPresenter;
import com.MovieApps.view.fragment.HomeFragment;
import com.MovieApps.view.fragment.views.DashboardView;

import javax.inject.Inject;

public class FragmentDashboardPresenter extends AbsPresenter<DashboardView> {
    private ApiService apiService;
    private PreferencesHelper preferencesHelper;

    @Inject
    public FragmentDashboardPresenter(ApiService api, PreferencesHelper helper) {
        apiService = api;
        preferencesHelper = helper;
    }

    public FragmentDashboardPresenter(HomeFragment HomeFragment, HomeFragment HomeFragment1, Context context) {
        super();
    }


}
