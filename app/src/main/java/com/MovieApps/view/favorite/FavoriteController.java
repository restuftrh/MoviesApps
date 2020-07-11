package com.MovieApps.view.favorite;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.model.favorite.FavoriteParam;
import com.MovieApps.view.commons.AbsController;
import com.MovieApps.view.main.MainActivity;
import com.MovieApps.widget.GridHeaderSpacingItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import net.derohimat.baseapp.ui.view.BaseImageView;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import dagger.Lazy;

public class FavoriteController extends AbsController implements FavoriteView {

    @Inject FavoritePresenter presenter;
    @Inject Lazy<ProgressDialog> dialogLazy;
    @Inject PreferencesHelper preference;
    @Inject
    FavoriteGridAdapter adapter;

    @Bind(R.id.btnBack) BaseImageView btnBack;
    @Bind(R.id.recyle_row) BaseRecyclerView recyclerViewGrid;
    @Bind(R.id.swipeRefresh) SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.btnMenu) BaseImageView menu;

    ProgressDialog mdialog;
    int id = 0;

    private List<FavoriteParam> favorite = new ArrayList<>();
    private List<FavoriteParam> selectedFavorite = new ArrayList<>();


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

        id = getArgs().getInt("id");

        if (preference.getFavorite() != null){
            favorite = preference.getFavorite();
        }

        adapter.addAll(favorite);
        setUpRecyclerGrid();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter.addAll(favorite);
                swipeRefresh.setRefreshing(false);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == 1){
                    MainActivity.start(getContext(), "0");
                }else  if(id == 2){
                    MainActivity.start(getContext(), "1");
                }
            }
        });
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
//                presenter.getMovies();
            }

            @Override
            public void onLoadMore() {
            }
        });

        adapter.setOnItemClickListener((view, i) -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("apakah anda ingin membookmark/unbookmark item?")
                    .setPositiveButton("Ya", (dialog, whichButton) -> {
                        if (favorite.size() > 0){
                            selectedFavorite.clear();
                            for (int j=0; j<favorite.size(); j++) {
                                FavoriteParam eachSeries = favorite.get(j);
                                if (favorite.get(i - 1).getId() == eachSeries.getId()) {
                                    selectedFavorite.add(eachSeries);
                                    favorite.remove(eachSeries);
                                    PreferencesHelper.deleteFavorite();
                                    preference.saveFavorite(favorite);
                                    break;
                                }
                            }

                        }

                        if (preference.getFavorite() != null){
                            favorite = preference.getFavorite();
                        }
                        adapter.clear();
                        adapter.addAll(favorite);
                        dialog.dismiss();
                    })
                    .setNegativeButton("Tidak", (dialog, whichButton) -> dialog.dismiss())
                    .show();
        });
    }

    @OnClick(R.id.btnMenu)
    public void onMoreMenuClick() {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(getContext(), menu);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.sortby_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sortby_nama:
                           Collections.sort(favorite, new Comparator<FavoriteParam>() {
                                @Override
                                public int compare(FavoriteParam o1, FavoriteParam o2) {
                                    try {
                                        return (o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase()));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return 0;
                                    }
                                }
                            });

                            adapter.clear();
                            adapter.addAll(favorite);

                        break;
                    case R.id.sortby_gendre:
                        Collections.sort(favorite, new Comparator<FavoriteParam>() {
                            @Override
                            public int compare(FavoriteParam o1, FavoriteParam o2) {
                                try {
                                    return String.valueOf(o1.getGenre_ids()).compareTo(String.valueOf(o2.getGenre_ids()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return 0;
                                }
                            }
                        });

                        adapter.clear();
                        adapter.addAll(favorite);
                        break;
                    case  R.id.sortby_score:
                        Collections.sort(favorite, new Comparator<FavoriteParam>() {
                            @Override
                            public int compare(FavoriteParam o1, FavoriteParam o2) {
                                try {
                                    return o2.getVote_average().compareTo(o1.getVote_average());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return 0;
                                }
                            }
                        });

                        adapter.clear();
                        adapter.addAll(favorite);
                        break;
                    case  R.id.sortby_date:
                        Collections.sort(favorite, new Comparator<FavoriteParam>() {
                            @Override
                            public int compare(FavoriteParam o1, FavoriteParam o2) {
                                try {
                                    return o2.getRelease_date().compareTo(o1.getRelease_date());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return 0;
                                }
                            }
                        });

                        adapter.clear();
                        adapter.addAll(favorite);
                        break;
                }
                return true;
            }
        });

        popup.show();//showing popup menu
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
    public void showError(Throwable throwable) {

    }

    @Override
    public void showLoading(boolean isShow, int loadingType) {

    }
}
