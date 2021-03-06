package com.MovieApps.view.favorite;

import com.MovieApps.mvp.ErrorView;
import com.MovieApps.mvp.LoadingView;


import net.derohimat.baseapp.view.BaseView;

import java.util.List;

public interface FavoriteView extends BaseView, ErrorView, LoadingView {

    void showResult(String message);
    void showFailedData(String title, String errorMessage);

    void setUpRecyclerGrid();

}
