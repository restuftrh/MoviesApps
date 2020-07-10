package com.MovieApps.view.fragment.presenter;

import android.content.Context;
import android.util.Log;

import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.util.error.ErrorHandler;
import com.MovieApps.view.commons.AbsPresenter;
import com.MovieApps.view.fragment.HomeFragment;
import com.MovieApps.view.fragment.SeriesFragment;
import com.MovieApps.view.fragment.views.DashboardView;
import com.MovieApps.view.fragment.views.SeriesView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FragmentSeriesPresenter extends AbsPresenter<SeriesView> {
    private ApiService apiService;
    private PreferencesHelper preferencesHelper;

    @Inject
    public FragmentSeriesPresenter(ApiService api, PreferencesHelper helper) {
        apiService = api;
        preferencesHelper = helper;
    }

    public FragmentSeriesPresenter(SeriesFragment SeriesFragment, SeriesFragment SeriesFragment1, Context context) {
        super();
    }

    public void getSeries() {
        getView().showLoading(true, 1);
        apiService.getSeries()
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
