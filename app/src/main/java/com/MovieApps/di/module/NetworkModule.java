package com.MovieApps.di.module;

import com.MovieApps.BuildConfig;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.data.remote.ApiService;
import com.MovieApps.data.remote.UnauthorisedInterceptor;
import com.MovieApps.util.Constant;
import com.github.simonpercic.oklog3.OkLogInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(UnauthorisedInterceptor unauthorisedInterceptor, PreferencesHelper preferencesHelper) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.addInterceptor(interceptor);
            // create an instance of OkLogInterceptor using a builder()
            OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder()
                    .withRequestHeaders(true)
                    .withRequestBody(true)
                    .withResponseUrl(true)
                    .shortenInfoUrl(true)
                    .build();

            builder.addInterceptor(okLogInterceptor);
        }

        //Extra Headers
//        builder.addNetworkInterceptor(chain -> {
//            Request request;
//            if (preferencesHelper.getLoginResponse() != null) {
//                request = chain.request()
//                        .newBuilder()
//                        .addHeader("Accept", "application/json")
//                        .addHeader("User-Type", "AGEN")
//                        .addHeader("Authorization", preferencesHelper.getLoginResponse().getAccessToken())
//                        .build();
//            } else {
//                request = chain.request()
//                        .newBuilder()
//                        .addHeader("Accept", "application/json")
//                        .addHeader("User-Type", "AGEN")
//                        .build();
//            }
//            return chain.proceed(request);
//        });

        builder.addInterceptor(unauthorisedInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        String baseUrl;
        if (BuildConfig.FLAVOR.equals("production")) {
            baseUrl = Constant.URL_PRODUCTION;
        } else {
            baseUrl = Constant.URL_STAGING;
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
