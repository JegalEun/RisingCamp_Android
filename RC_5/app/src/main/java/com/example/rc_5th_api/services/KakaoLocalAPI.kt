package com.example.rc_5th_api.services

import com.example.rc_5th_api.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoLocalAPI {

    @Headers("Authorization: KakaoAK ${BuildConfig.KAKAO_API_KEY}")
    @GET("/v2/local/geo/transcoord.json?output_coord=TM")
    suspend fun getTmCoordinates(
        @Query("x") longitude : Double,
        @Query("y") latitude : Double,
    ) : Response<TmCoordinatesResponse>
}