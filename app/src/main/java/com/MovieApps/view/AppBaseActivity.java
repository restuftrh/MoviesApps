package com.MovieApps.view;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;

import com.MovieApps.CrsApp;
import com.MovieApps.di.component.ActivityComponent;
import com.MovieApps.di.component.DaggerActivityComponent;
import com.MovieApps.di.module.ActivityModule;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.view.BaseView;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class AppBaseActivity extends BaseActivity {

    private ActivityComponent component;
    private BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        ButterKnife.bind(this);
        Timber.tag(getClass().getSimpleName());
        mInflater = LayoutInflater.from(mContext);
        onViewReady(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    protected <V extends BaseView> void bindPresenter(V view, BasePresenter<V> p) {
        presenter = p;
        presenter.attachView(view);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    public ActivityComponent getComponent() {
        if (component == null) {
            component = DaggerActivityComponent.builder()
                    .applicationComponent(CrsApp.component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return component;
    }

    protected CrsApp getApp() {
        return (CrsApp) getApplicationContext();
    }

    protected void setStatusBarResource(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, resId));
        }
    }
}
