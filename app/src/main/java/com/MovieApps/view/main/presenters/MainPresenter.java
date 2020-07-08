package com.MovieApps.view.main.presenters;

import com.MovieApps.data.remote.ApiService;
import com.MovieApps.view.commons.AbsPresenter;
import com.MovieApps.view.main.views.MainView;

import javax.inject.Inject;

public class MainPresenter extends AbsPresenter<MainView> {

    private ApiService apiService;

    @Inject
    MainPresenter(ApiService api) {
        apiService = api;
    }

//    public Subscription loadBadge() {
//        return apiService.getCartItem()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(Transformers.applyApiCall())
//                .subscribe(apiResponse ->
//                                getView().showBadge(apiResponse.getData())
//                        , ErrorHandler::handleError);
//    }

}
