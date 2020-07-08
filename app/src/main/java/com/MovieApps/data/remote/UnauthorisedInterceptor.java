package com.MovieApps.data.remote;

import android.os.Handler;
import android.os.Looper;

import com.MovieApps.CrsApp;
import com.MovieApps.events.AuthenticationErrorEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {

    @Inject EventBus eventBus;

    @Inject
    UnauthorisedInterceptor() {
        CrsApp.component().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        String url = chain.request().url().toString();

        if (response.code() == 401 && !url.contains("/oauth/token")) {
            new Handler(Looper.getMainLooper()).post(() -> eventBus.post(new AuthenticationErrorEvent()));
        }
        return response;
    }
}