package com.MovieApps.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MovieApps.model.favorite.FavoriteMoviesParam;
import com.MovieApps.model.movies.ListMoviesResponse;
import com.MovieApps.model.movies.MoviesResponse;
import com.MovieApps.view.favorite.FavoriteActivity;
import com.MovieApps.view.fragment.Adapter.MoviesGridAdapter;
import com.MovieApps.view.fragment.presenter.FragmentDashboardPresenter;
import com.MovieApps.widget.GridHeaderSpacingItemDecoration;
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Controller;
import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.di.component.ActivityComponent;
import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.view.AppBaseActivity;
import com.MovieApps.view.fragment.views.DashboardView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.view.BaseImageView;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.Bind;

public class HomeFragment extends Fragment implements DashboardView {


    @Inject PreferencesHelper preferencesHelper;
    @Inject FragmentDashboardPresenter presenter;
    @Inject MoviesGridAdapter adapter;

    BaseRecyclerView recyclerViewGrid;
    SwipeRefreshLayout swipeRefresh;
    BaseImageView image;

    @Bind(com.MovieApps.R.id.main_child_container) ChangeHandlerFrameLayout childContainer;

    ProgressDialog mdialog;

    private List<ListMoviesResponse> response;
    private List<FavoriteMoviesParam> favoriteMovies = new ArrayList<>();

    public static void start(Context context) {
        context.startActivity(new Intent(
                context,
                HomeFragment.class
        ));
    }

    private final List<Controller.LifecycleListener> lifecycleListeners = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        presenter = new FragmentDashboardPresenter(this, this, getContext());
        getActivityComponent().inject(this);
        bindPresenter(this, presenter);

        recyclerViewGrid = view.findViewById(R.id.recyle_row);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        image = view.findViewById(R.id.image_favoritePage);

        presenter.getMovies();

        if (preferencesHelper.getFavoriteMovies() != null){
            favoriteMovies = preferencesHelper.getFavoriteMovies();
        }


        mdialog = new ProgressDialog(getContext());
        mdialog.setMessage("Please Wait");
        mdialog.show();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                presenter.getMovies();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity.start(getContext());
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

    public void bindPresenter(DashboardView mvpView, FragmentDashboardPresenter presenter) {
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
    public void showData(MoviesResponse data) {
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
                presenter.getMovies();
            }

            @Override
            public void onLoadMore() {
            }
        });

        adapter.setOnItemClickListener((view, i) -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("apakah anda ingin membookmark/unbookmark item?")
                    .setPositiveButton("Ya", (dialog, whichButton) -> {
                        FavoriteMoviesParam eachData = new FavoriteMoviesParam(
                                response.get(i - 1).getPopularity(),response.get(i - 1).getVote_count(),response.get(i - 1).getVideo(),
                                response.get(i - 1).getPoster_path(),response.get(i - 1).getGenre_ids()[0],response.get(i - 1).getId(),
                                1,response.get(i - 1).getAdult(),response.get(i - 1).getBackdrop_path(),response.get(i - 1).getOriginal_language(),
                                response.get(i - 1).getOriginal_title(),response.get(i - 1).getTitle(),response.get(i - 1).getVote_average(),
                                response.get(i - 1).getOverview(),response.get(i - 1).getRelease_date()
                        );
                        favoriteMovies.add(eachData);
                        PreferencesHelper.deleteFavoriteMovie();
                        preferencesHelper.saveFavoriteMovies(favoriteMovies);
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
