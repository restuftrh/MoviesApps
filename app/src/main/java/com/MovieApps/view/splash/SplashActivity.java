package com.MovieApps.view.splash;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.view.AppBaseActivity;
import com.MovieApps.view.main.MainActivity;

import net.derohimat.baseapp.ui.view.BaseImageView;

import javax.inject.Inject;

import butterknife.Bind;

public class SplashActivity extends AppBaseActivity {

    private static final int SPLASH_DURATION = 1000;

    @Bind(R.id.splash_logo) BaseImageView splash;
    @Bind(R.id.splash_layout) RelativeLayout layout;

    @Inject PreferencesHelper mPreferencesHelper;

    @Override
    protected int getResourceLayout() {
        return com.MovieApps.R.layout.activity_splash;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);


        new Handler().postDelayed(() -> {
            MainActivity.start(mContext);
            finish();
        }, SPLASH_DURATION);
    }
}
