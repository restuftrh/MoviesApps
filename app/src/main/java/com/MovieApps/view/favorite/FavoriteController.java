package com.MovieApps.view.favorite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.model.favorite.FavoriteMoviesParam;
import com.MovieApps.model.favorite.FavoriteSeriesParam;
import com.MovieApps.view.commons.AbsController;
import com.MovieApps.widget.GridHeaderSpacingItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.view.BaseImageView;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import dagger.Lazy;

public class FavoriteController extends AbsController implements FavoriteView {

    @Inject FavoritePresenter presenter;
    @Inject Lazy<ProgressDialog> dialogLazy;
    @Inject PreferencesHelper preference;
    @Inject FavoriteMoviesGridAdapter adapter;
    @Inject FavoriteSeriesGridAdapter adapter2;

    @Bind(R.id.btnBack) BaseImageView btnBack;
    @Bind(R.id.recyle_row) BaseRecyclerView recyclerViewGrid;
    @Bind(R.id.recyle_row_series) BaseRecyclerView recyclerViewGridSeries;
    @Bind(R.id.swipeRefresh) SwipeRefreshLayout swipeRefresh;

    ProgressDialog mdialog;

    private List<FavoriteMoviesParam> favoriteMovies = new ArrayList<>();
    private List<FavoriteSeriesParam> favoriteSeries = new ArrayList<>();


    public FavoriteController(Bundle args) {
        super(args);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onSetupComponent() {
        getActivityComponent().inject(this);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_favorite; }

    @Override
    protected void onViewBound(View view) {
        bindPresenter(this, presenter);

        if (preference.getFavoriteMovies() != null){
            favoriteMovies = preference.getFavoriteMovies();
        }
        adapter.addAll(favoriteMovies);
        setUpRecyclerGrid();

        if (preference.getFavoriteSeries() != null){
            favoriteSeries = preference.getFavoriteSeries();
        }
        adapter2.addAll(favoriteSeries);
        setUpRecyclerGrid2();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter2.clear();
                adapter.addAll(favoriteMovies);
                adapter2.addAll(favoriteSeries);
                swipeRefresh.setRefreshing(false);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    @Override
    public void setUpRecyclerGrid() {
        recyclerViewGrid.setUpAsGrid(2);
        recyclerViewGrid.addItemDecoration(new GridHeaderSpacingItemDecoration(2, 0, true, true));
        recyclerViewGrid.setAdapter(adapter);
        recyclerViewGrid.setHasFixedSize(true);
        recyclerViewGridSeries.setScrollContainer(false);
        recyclerViewGrid.setPullRefreshEnabled(false);
        recyclerViewGrid.setLoadingMoreEnabled(false);
        recyclerViewGrid.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                presenter.getMovies();
            }

            @Override
            public void onLoadMore() {
            }
        });

        adapter.setOnItemClickListener((view, i) -> {
//            new AlertDialog.Builder(getContext())
//                    .setMessage("apakah anda ingin membookmark/unbookmark item?")
//                    .setPositiveButton("Ya", (dialog, whichButton) -> {
//                        dialog.dismiss();
//                        FavoriteMoviesResponse eachData = new FavoriteMoviesResponse(
//                                response.get(i).getPopularity(),response.get(i).getVote_count(),response.get(i).getVideo(),
//                                response.get(i).getPoster_path(),response.get(i).getGenre_ids()[0],response.get(i).getId(),
//                                1,response.get(i).getAdult(),response.get(i).getBackdrop_path(),response.get(i).getOriginal_language(),
//                                response.get(i).getOriginal_title(),response.get(i).getTitle(),response.get(i).getVote_average(),
//                                response.get(i).getOverview(),response.get(i).getRelease_date()
//                        );
//                        favoriteMovies.add(eachData);
//                        preferencesHelper.saveFavoriteMovies(favoriteMovies);
//                    })
//                    .setNegativeButton("Tidak", (dialog, whichButton) -> dialog.dismiss())
//                    .show();
        });
    }

    @Override
    public void setUpRecyclerGrid2() {
        recyclerViewGridSeries.setUpAsGrid(2);
        recyclerViewGridSeries.addItemDecoration(new GridHeaderSpacingItemDecoration(2, 0, true, true));
        recyclerViewGridSeries.setAdapter(adapter2);
        recyclerViewGridSeries.setHasFixedSize(true);
        recyclerViewGridSeries.setScrollContainer(false);
        recyclerViewGridSeries.setPullRefreshEnabled(false);
        recyclerViewGridSeries.setLoadingMoreEnabled(false);
        recyclerViewGridSeries.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                presenter.getMovies();
            }

            @Override
            public void onLoadMore() {
            }
        });

        adapter.setOnItemClickListener((view, i) -> {
//            new AlertDialog.Builder(getContext())
//                    .setMessage("apakah anda ingin membookmark/unbookmark item?")
//                    .setPositiveButton("Ya", (dialog, whichButton) -> {
//                        dialog.dismiss();
//                        FavoriteMoviesResponse eachData = new FavoriteMoviesResponse(
//                                response.get(i).getPopularity(),response.get(i).getVote_count(),response.get(i).getVideo(),
//                                response.get(i).getPoster_path(),response.get(i).getGenre_ids()[0],response.get(i).getId(),
//                                1,response.get(i).getAdult(),response.get(i).getBackdrop_path(),response.get(i).getOriginal_language(),
//                                response.get(i).getOriginal_title(),response.get(i).getTitle(),response.get(i).getVote_average(),
//                                response.get(i).getOverview(),response.get(i).getRelease_date()
//                        );
//                        favoriteMovies.add(eachData);
//                        preferencesHelper.saveFavoriteMovies(favoriteMovies);
//                    })
//                    .setNegativeButton("Tidak", (dialog, whichButton) -> dialog.dismiss())
//                    .show();
        });
    }


    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showResult(String message) {

    }

    @Override
    public void showFailedData(String title, String errorMessage) {

    }

    @Override
    public void showFavoriteMovies(List<FavoriteMoviesParam> data) {

    }



    @Override
    public void showFavoriteSeries(List<FavoriteSeriesParam> data) {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showLoading(boolean isShow, int loadingType) {

    }
}
