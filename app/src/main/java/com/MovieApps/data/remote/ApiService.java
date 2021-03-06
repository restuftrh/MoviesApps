package com.MovieApps.data.remote;

import com.MovieApps.model.common.ApiResponse;
import com.MovieApps.model.movies.MoviesResponse;
import com.MovieApps.model.series.SeriesResponse;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface ApiService {

    /* --------------------------------------------------- */
    /* > Auth */
    /* --------------------------------------------------- */

    @GET("movies")
    Observable<MoviesResponse>
    getMovies();

    @GET("tvs")
    Observable<SeriesResponse>
    getSeries();
}