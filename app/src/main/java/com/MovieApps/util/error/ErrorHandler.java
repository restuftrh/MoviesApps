package com.MovieApps.util.error;

import com.MovieApps.mvp.ErrorView;

import retrofit2.adapter.rxjava.HttpException;
import timber.log.Timber;

public class ErrorHandler {

    public static void handleError(Throwable throwable) {
        Timber.e(throwable, throwable.getMessage());
    }

    public static void handleError(Throwable throwable, ErrorView errorView) {
        handleError(throwable);

        if (errorView != null) {
            errorView.showError(throwable);
        }
    }

    public static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }
}
