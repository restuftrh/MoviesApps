package com.MovieApps.util;

import androidx.annotation.StringRes;

import com.MovieApps.CrsApp;

public class ContextProvider {

    public static String getString(@StringRes int resId) {
        return CrsApp.appContext().getString(resId);
    }
}
