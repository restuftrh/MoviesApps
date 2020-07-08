package com.MovieApps.util.common;

import java.io.Closeable;

public class Closeables {

    public static void closeSilenty(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception ignored) {
        }
    }
}
