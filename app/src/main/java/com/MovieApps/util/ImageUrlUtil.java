package com.MovieApps.util;

import com.MovieApps.BuildConfig;

public class ImageUrlUtil {
    public static String getBaseUrl() {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/v1/product/image/";
        } else {
            return Constant.URL_STAGING + "api/v1/product/image/";
        }
    }

    public static String getBaseUrlModule() {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/v1/module/image/";
        } else {
            return Constant.URL_STAGING + "api/v1/module/image/";
        }
    }

    public static String getUrlPromo(int idPromo) {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/v1/promosi/" + idPromo + "/image";
        } else {
            return Constant.URL_STAGING + "api/v1/promosi/" + idPromo + "/image";
        }
    }

    public static String getFotoTuk(int idTuk) {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/v1/tuk/foto/" + idTuk;
        } else {
            return Constant.URL_STAGING + "api/v1/tuk/foto/" + idTuk;
        }
    }

    public static String getDenahTuk(int idTuk) {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/v1/tuk/denah/" + idTuk;
        } else {
            return Constant.URL_STAGING + "api/v1/tuk/denah/" + idTuk;
        }
    }

    public static String getUserPhoto(int id) {
        if (BuildConfig.FLAVOR.equals("production")) {
            return Constant.URL_PRODUCTION + "api/user/"+id+"/photo";
        } else {
            return Constant.URL_STAGING + "api/user/"+id+"/photo";
        }
    }
}
