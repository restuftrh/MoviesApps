package com.MovieApps.view.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.MovieApps.view.fragment.SeriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.events.BadgeEvent;
import com.MovieApps.view.commons.AbsController;
import com.MovieApps.view.fragment.HomeFragment;
import com.MovieApps.view.main.presenters.MainPresenter;
import com.MovieApps.view.main.views.MainView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.Bind;

import static java.sql.Types.NULL;

public class MainController extends AbsController implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.bn_main)
    BottomNavigationView bottomNavigationView;

    @Inject
    MainPresenter presenter;
    @Inject
    PreferencesHelper preference;

    private int sortcut = NULL;

    public MainController(Bundle args) {
        super(args);
    }

    @Override
    protected void onSetupComponent() {
        getComponent().inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return com.MovieApps.R.layout.controller_main;
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onViewBound(View view) {
        bindPresenter(this, presenter);
        // kita set default nya Home Fragment

        if (getArgs().getString(MainController.class.getName()) != null) {
            sortcut = Integer.parseInt(getArgs().getString(MainController.class.getName()));
            changeView(sortcut);
        }else{
            loadFragment(new HomeFragment());
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (sortcut != NULL){
            if (sortcut == 0){
                bottomNavigationView.setSelectedItemId(R.id.home_menu);
            }else if(sortcut == 1){
                bottomNavigationView.setSelectedItemId(R.id.series_bot_nav);
            }
        }


    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    protected void onActivityResumed(@NonNull Activity activity) {
        super.onActivityResumed(activity);
//        presenter.loadBadge();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBadgeEvent(BadgeEvent event) {
//        presenter.loadBadge();
    }

    private boolean changeView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new SeriesFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return loadFragment(fragment);
    }


    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                changeView(0);
                break;
            case R.id.series_bot_nav:
                changeView(1);
                break;
        }
        return true;
    }

}
