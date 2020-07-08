package com.MovieApps.data.remote;

import com.MovieApps.model.common.ApiResponse;




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

    @Multipart
    @POST("/api/user/photo")
    Observable<ApiResponse>
    uploadPhoto(@Part MultipartBody.Part photo);


}