package com.MovieApps.view.fragment.views;

import com.MovieApps.model.movies.ListMoviesResponse;
import com.MovieApps.model.movies.MoviesResponse;
import com.MovieApps.mvp.ErrorView;
import com.midtrans.sdk.uikit.abstracts.BaseView;
import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.mvp.LoadingView;

import java.util.List;

public interface DashboardView extends BaseView, ErrorView, LoadingView {

    void showResult(ApiResponse data);

    void showProgress(boolean isShow);

    void showFailedData(String title, String errorMessage);

    void showData(MoviesResponse data);


    void setUpRecyclerGrid();
}
