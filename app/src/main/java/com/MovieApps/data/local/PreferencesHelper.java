package com.MovieApps.data.local;

import android.content.Context;


import com.MovieApps.model.favorite.FavoriteParam;
import com.orhanobut.hawk.BuildConfig;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import net.derohimat.baseapp.util.BasePreferenceUtils;

import java.util.List;


public class PreferencesHelper extends BasePreferenceUtils {

    private static final String KEY_TOKEN = "Key.DeviceToken";
    private static final String KEY_FAVORITE = "Key.Favorite";

    public PreferencesHelper(Context context) {
        Hawk.init(context)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(context))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();
    }


    public void saveDeviceToken(String token) {
        Hawk.put(KEY_TOKEN, token);
    }

    public String getDeviceToken() {
        return Hawk.get(KEY_TOKEN);
    }

    public void saveFavorite(List<FavoriteParam> favorite) {
        Hawk.put(KEY_FAVORITE, favorite);
    }

    public static void deleteFavorite() {
        Hawk.remove(KEY_FAVORITE);
    }


    public static List<FavoriteParam> getFavorite() {
        return Hawk.get(KEY_FAVORITE);
    }




    public static void clear() {
        Hawk.clear();
    }
}
