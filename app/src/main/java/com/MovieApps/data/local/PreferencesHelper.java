package com.MovieApps.data.local;

import android.content.Context;


import com.orhanobut.hawk.BuildConfig;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import net.derohimat.baseapp.util.BasePreferenceUtils;


public class PreferencesHelper extends BasePreferenceUtils {

    private static final String KEY_TOKEN = "Key.DeviceToken";

    public PreferencesHelper(Context context) {
        Hawk.init(context)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(context))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();
    }

    /* --------------------------------------------------- */
    /* > Login Response */
    /* --------------------------------------------------- */


    public void saveDeviceToken(String token) {
        Hawk.put(KEY_TOKEN, token);
    }

    public String getDeviceToken() {
        return Hawk.get(KEY_TOKEN);
    }


    public static void clear() {
        Hawk.clear();
    }
}
