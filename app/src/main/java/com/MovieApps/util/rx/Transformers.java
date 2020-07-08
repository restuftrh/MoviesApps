package com.MovieApps.util.rx;

import android.os.Handler;
import android.os.Looper;

import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.model.exception.ApiError;
import com.MovieApps.mvp.LoadingType;
import com.MovieApps.mvp.LoadingView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Transformers {

    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static <T> rx.Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static Observable.Transformer<LoginResponse, LoginResponse> applyApiCallLogin() {
//        return apiResponseObservable -> apiResponseObservable.compose(applySchedulers())
//                .switchMap(tApiResponse -> {
//                    if (tApiResponse.getHttpStatus() >= 400 && tApiResponse.getHttpStatus() <= 599) {
//                        return Observable.error(new ApiError(tApiResponse));
//                    } else {
//                        return Observable.just(tApiResponse);
//                    }
//                });
//    }

    public static Observable.Transformer<ApiResponse, ApiResponse> applyApiCallBase() {
        return apiResponseObservable -> apiResponseObservable.compose(applySchedulers())
                .switchMap(tApiResponse -> {
                    if (tApiResponse.getHttpStatus() >= 400 && tApiResponse.getHttpStatus() <= 599) {
                        return Observable.error(new ApiError(tApiResponse));
                    } else {
                        return Observable.just(tApiResponse);
                    }
                });
    }

    public static <T> rx.Observable.Transformer<ApiResponse<T>, ApiResponse<T>> applyApiCall() {
        return apiResponseObservable -> apiResponseObservable.compose(applySchedulers())
                .switchMap(tApiResponse -> {
                    if (tApiResponse.getHttpStatus() >= 400 && tApiResponse.getHttpStatus() <= 599) {
                        return Observable.error(new ApiError(tApiResponse));
                    } else {
                        return Observable.just(tApiResponse);
                    }
                });
    }


    public static <T> rx.Observable.Transformer<T, T> notifyProgress(LoadingView loadingView) {
        return tObservable -> tObservable.doOnSubscribe(() -> MAIN_HANDLER
                .post(() -> loadingView.showLoading(true, LoadingType.ANY)))
                .doOnTerminate(() -> MAIN_HANDLER
                        .post(() -> loadingView.showLoading(false, LoadingType.ANY)));
    }
}
