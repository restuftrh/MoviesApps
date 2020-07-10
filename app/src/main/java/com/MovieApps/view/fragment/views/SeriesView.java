package com.MovieApps.view.fragment.views;

import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.model.movies.MoviesResponse;
import com.MovieApps.model.series.SeriesResponse;
import com.MovieApps.mvp.ErrorView;
import com.MovieApps.mvp.LoadingView;
import com.midtrans.sdk.uikit.abstracts.BaseView;

public interface SeriesView extends BaseView, ErrorView, LoadingView {

    void showResult(ApiResponse data);

    void showProgress(boolean isShow);

    void showFailedData(String title, String errorMessage);

    void showData(SeriesResponse data);


    void setUpRecyclerGrid();
}
