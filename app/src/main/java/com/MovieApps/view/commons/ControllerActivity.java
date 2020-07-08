package com.MovieApps.view.commons;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.MovieApps.view.AppBaseActivity;

import butterknife.Bind;
import rx.functions.Action1;

public abstract class ControllerActivity extends AppBaseActivity {

    @Bind(com.MovieApps.R.id.container) ViewGroup container;
    @Bind(com.MovieApps.R.id.toolbar) Toolbar toolbar;

    private Router router;

    @Override
    protected int getResourceLayout() {
        return com.MovieApps.R.layout.activity_router_container;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        router = Conductor.attachRouter(this, container, bundle);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(getController()));
        }
        initToolbar(toolbar);
    }

    public void editToolbar(Action1<Toolbar> fun) {
        fun.call(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    protected abstract Toolbar initToolbar(Toolbar toolbar);

    protected abstract Controller getController();
}
