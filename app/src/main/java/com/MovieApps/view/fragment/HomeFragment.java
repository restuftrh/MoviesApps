package com.MovieApps.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MovieApps.model.movies.ListMoviesResponse;
import com.MovieApps.model.movies.MoviesResponse;
import com.MovieApps.view.fragment.Adapter.MoviesGridAdapter;
import com.MovieApps.view.fragment.presenter.FragmentDashboardPresenter;
import com.MovieApps.widget.GridHeaderSpacingItemDecoration;
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Controller;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.di.component.ActivityComponent;
import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.view.AppBaseActivity;
import com.MovieApps.view.fragment.views.DashboardView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.view.BaseRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.Bind;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;

public class HomeFragment extends Fragment implements DashboardView {


    @Inject PreferencesHelper preferencesHelper;
    @Inject FragmentDashboardPresenter presenter;
    @Inject MoviesGridAdapter adapter;

    BaseRecyclerView recyclerViewGrid;

    @Bind(com.MovieApps.R.id.main_child_container) ChangeHandlerFrameLayout childContainer;

    ProgressDialog mdialog;

    private List<ListMoviesResponse> response;

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

        presenter.getMovies();

        mdialog = new ProgressDialog(getContext());
        mdialog.setMessage("Please Wait");
        mdialog.show();

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
//            ClubAnotherResponse selectedItem = adapter.getDatas().get(i - 1);
//            DetailKomunitasActivity.start(getContext(), selectedItem.getIdClub());
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
