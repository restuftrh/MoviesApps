package com.MovieApps.view.fragment.presenter;

import android.content.Context;
import android.util.Log;

import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.util.error.ErrorHandler;
import com.MovieApps.view.commons.AbsPresenter;
import com.MovieApps.view.fragment.HomeFragment;
import com.MovieApps.view.fragment.views.DashboardView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void getMovies() {
        getView().showLoading(true, 1);
        apiService.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> getView().showLoading(false, 1))
                .subscribe(apiResponse -> {
                    getView().showData(apiResponse);
                }, throwable -> {
                    Log.d("error lier aslina lurr", "" + throwable);
                    ErrorHandler.handleError(throwable);
                    getView().showFailedData("Error", throwable.getMessage());
                });
    }



}
