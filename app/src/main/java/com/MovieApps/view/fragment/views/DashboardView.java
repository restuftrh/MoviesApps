package com.MovieApps.view.fragment.views;

import com.midtrans.sdk.uikit.abstracts.BaseView;
import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.mvp.LoadingView;

public interface DashboardView extends BaseView, LoadingView {
    void showResult(ApiResponse data);

    void showProgress(boolean isShow);

    void showFailedData(String title, String errorMessage);


    void showFailedKirim(String title, String errorMessage);

    void showSuksesKirim(String message);
}
