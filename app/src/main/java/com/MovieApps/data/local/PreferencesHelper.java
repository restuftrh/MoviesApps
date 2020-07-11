package com.MovieApps.data.local;

import android.content.Context;


import com.MovieApps.model.favorite.FavoriteMoviesParam;
import com.MovieApps.model.favorite.FavoriteSeriesParam;
import com.orhanobut.hawk.BuildConfig;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import net.derohimat.baseapp.util.BasePreferenceUtils;

import java.util.List;


public class PreferencesHelper extends BasePreferenceUtils {

    private static final String KEY_TOKEN = "Key.DeviceToken";
    private static final String KEY_FAVORITE_MOVIES = "Key.FavoriteMovies";
    private static final String KEY_FAVORITE_Series = "Key.FavoriteSeries";

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

    public void saveFavoriteMovies(List<FavoriteMoviesParam> favoriteMovies) {
        Hawk.put(KEY_FAVORITE_MOVIES, favoriteMovies);
    }

    public static void deleteFavoriteMovie() {
        Hawk.remove(KEY_FAVORITE_MOVIES);
    }


    public static List<FavoriteMoviesParam> getFavoriteMovies() {
        return Hawk.get(KEY_FAVORITE_MOVIES);
    }

    public void saveFavoriteSries(List<FavoriteSeriesParam> favoriteSeries) {
        Hawk.put(KEY_FAVORITE_Series, favoriteSeries);
    }

    public static void deleteFavoriteSeries() {
        Hawk.remove(KEY_FAVORITE_Series);
    }


    public static List<FavoriteSeriesParam> getFavoriteSeries() {
        return Hawk.get(KEY_FAVORITE_Series);
    }


    public static void clear() {
        Hawk.clear();
    }
}
