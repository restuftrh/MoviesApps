package com.MovieApps.util;

import androidx.annotation.StringRes;
import android.widget.Toast;

import com.MovieApps.CrsApp;

import io.fabric.sdk.android.services.common.SafeToast;

public class Toasts {

    public static void show(Throwable throwable) {
        if (throwable != null) {
            show(throwable.getMessage());
        }
    }

    public static void show(@StringRes int resid) {
        show(ContextProvider.getString(resid));
    }

    public static void show(String message) {
        if (!Strings.isEmpty(message)) {
            SafeToast.makeText(CrsApp.appContext(), message, Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
