package com.MovieApps.view.commons;

import android.os.Bundle;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.MovieApps.view.AppBaseActivity;

import butterknife.Bind;

public abstract class MainControllerActivity extends AppBaseActivity {

    @Bind(com.MovieApps.R.id.container) ViewGroup container;
    private Router router;

    @Override
    protected int getResourceLayout() {
        return com.MovieApps.R.layout.activity_main_router_container;
    }

    @Override
    protected void onViewReady(Bundle bundle) {
        router = Conductor.attachRouter(this, container, bundle);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(getController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    protected abstract Controller getController();
}
