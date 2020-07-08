package com.MovieApps.font;

import android.app.Application;

import com.MovieApps.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontChange extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/robotoreguler.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}

