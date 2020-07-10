package com.MovieApps.view.favorite;


import com.MovieApps.CrsApp;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.view.commons.AbsPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FavoritePresenter extends AbsPresenter<FavoriteView> {

    @Inject ApiService apiService;
    @Inject EventBus eventBus;
    @Inject PreferencesHelper preference;

    @Inject
    FavoritePresenter() {
        CrsApp.component().inject(this);
    }

}
