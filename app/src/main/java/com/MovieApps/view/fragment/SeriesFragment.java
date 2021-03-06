package com.MovieApps.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.di.component.ActivityComponent;
import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.model.favorite.FavoriteParam;
import com.MovieApps.model.series.ListSeriesResponse;
import com.MovieApps.model.series.SeriesResponse;
import com.MovieApps.view.AppBaseActivity;
import com.MovieApps.view.favorite.FavoriteActivity;
import com.MovieApps.view.fragment.Adapter.SeriesGridAdapter;
import com.MovieApps.view.fragment.presenter.FragmentSeriesPresenter;
import com.MovieApps.view.fragment.views.SeriesView;
import com.MovieApps.widget.GridHeaderSpacingItemDecoration;
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Controller;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.view.BaseImageView;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.Bind;

public class SeriesFragment extends Fragment implements SeriesView {


    @Inject PreferencesHelper preferencesHelper;
    @Inject FragmentSeriesPresenter presenter;
    @Inject SeriesGridAdapter adapter;

    BaseRecyclerView recyclerViewGrid;
    SwipeRefreshLayout swipeRefresh;
    BaseImageView image;

    @Bind(R.id.main_child_container) ChangeHandlerFrameLayout childContainer;

    ProgressDialog mdialog;

    private List<ListSeriesResponse> response;
    private List<FavoriteParam> favoriteSeries = new ArrayList<>();
    private List<FavoriteParam> selectedSeries = new ArrayList<>();

    public static void start(Context context) {
        context.startActivity(new Intent(
                context,
                SeriesFragment.class
        ));
    }

    private final List<Controller.LifecycleListener> lifecycleListeners = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        presenter = new FragmentSeriesPresenter(this, this, getContext());
        getActivityComponent().inject(this);
        bindPresenter(this, presenter);

        recyclerViewGrid = view.findViewById(R.id.recyle_row);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        image = view.findViewById(R.id.image_favoritePage);

        presenter.getSeries();
        if (preferencesHelper.getFavorite() != null){
            favoriteSeries = preferencesHelper.getFavorite();
        }

        mdialog = new ProgressDialog(getContext());
        mdialog.setMessage("Please Wait");
        mdialog.show();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                presenter.getSeries();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity.start(getContext(), 2);
            }
        });

        return view;
    }

    public ActivityComponent getActivityComponent() {
        AppBaseActivity absDriverAct = (AppBaseActivity) getActivity();
        if (absDriverAct == null) {
            throw new IllegalStateException("Activity should not be null at this state. Please use this on #onSetupComponent");
        }
        return absDriverAct.getComponent();
    }

    public void bindPresenter(SeriesView mvpView, FragmentSeriesPresenter presenter) {
        presenter.attachView(mvpView);

        addLifecycleListener(new Controller.LifecycleListener() {
            @Override
            public void preAttach(@NonNull Controller controller, @NonNull View view) {
                super.preAttach(controller, view);
                presenter.attachView(mvpView);
            }

            @Override
            public void postDestroy(@NonNull Controller controller) {
                super.postDestroy(controller);
                presenter.detachView();
            }
        });
    }

    public void addLifecycleListener(@NonNull Controller.LifecycleListener lifecycleListener) {
        if (!lifecycleListeners.contains(lifecycleListener)) {
            lifecycleListeners.add(lifecycleListener);
        }
    }


    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void showResult(ApiResponse data) {

    }

    @Override
    public void showProgress(boolean isShow) {

    }

    @Override
    public void showFailedData(String title, String errorMessage) {

    }

    @Override
    public void showData(SeriesResponse data) {
        response = data.getResults();
        mdialog.dismiss();
        adapter.clear();
        adapter.addAll(data.getResults());
        swipeRefresh.setRefreshing(false);
        setUpRecyclerGrid();
    }


    @Override
    public void setUpRecyclerGrid() {
        recyclerViewGrid.setUpAsGrid(2);
        recyclerViewGrid.addItemDecoration(new GridHeaderSpacingItemDecoration(2, 0, true, true));
        recyclerViewGrid.setAdapter(adapter);
        recyclerViewGrid.setHasFixedSize(true);
        recyclerViewGrid.setPullRefreshEnabled(false);
        recyclerViewGrid.setLoadingMoreEnabled(false);
        recyclerViewGrid.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getSeries();
            }

            @Override
            public void onLoadMore() {
            }
        });

        adapter.setOnItemClickListener((view, i) -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("apakah anda ingin membookmark/unbookmark item?")
                    .setPositiveButton("Ya", (dialog, whichButton) -> {
                        if (favoriteSeries.size() > 0){
                            selectedSeries.clear();
                            for (int j=0; j<favoriteSeries.size(); j++) {
                                FavoriteParam eachSeries = favoriteSeries.get(j);
                                if (response.get(i - 1).getId() == eachSeries.getId()) {
                                    selectedSeries.add(eachSeries);
                                    favoriteSeries.remove(eachSeries);
                                    PreferencesHelper.deleteFavorite();
                                    preferencesHelper.saveFavorite(favoriteSeries);
                                    break;
                                }
                            }

                            if (selectedSeries.size() == 0){
                                FavoriteParam eachData = new FavoriteParam(
                                        response.get(i - 1).getPoster_path(),response.get(i - 1).getGenre_ids()[0],response.get(i - 1).getId(),
                                        response.get(i - 1).getOriginal_name(),response.get(i - 1).getName(),response.get(i - 1).getVote_average(),
                                        response.get(i - 1).getOverview(),response.get(i - 1).getFirst_air_date()
                                );
                                favoriteSeries.add(eachData);
                                PreferencesHelper.deleteFavorite();
                                preferencesHelper.saveFavorite(favoriteSeries);
                            }
                        }else{
                            FavoriteParam eachData = new FavoriteParam(
                                    response.get(i - 1).getPoster_path(),response.get(i - 1).getGenre_ids()[0],response.get(i - 1).getId(),
                                    response.get(i - 1).getOriginal_name(),response.get(i - 1).getName(),response.get(i - 1).getVote_average(),
                                    response.get(i - 1).getOverview(),response.get(i - 1).getFirst_air_date()
                            );
                            favoriteSeries.add(eachData);
                            PreferencesHelper.deleteFavorite();
                            preferencesHelper.saveFavorite(favoriteSeries);
                        }

                        presenter.getSeries();

                        dialog.dismiss();
                    })
                    .setNegativeButton("Tidak", (dialog, whichButton) -> dialog.dismiss())
                    .show();
        });
    }



    @Override
    public void onNullInstanceSdk() {

    }

    @Override
    public void showLoading(boolean isShow, int loadingType) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}
