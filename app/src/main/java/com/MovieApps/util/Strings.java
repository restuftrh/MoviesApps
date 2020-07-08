package com.MovieApps.util;

import java.util.Locale;

public class Strings {

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.getDefault(), format, args);
    }
}
