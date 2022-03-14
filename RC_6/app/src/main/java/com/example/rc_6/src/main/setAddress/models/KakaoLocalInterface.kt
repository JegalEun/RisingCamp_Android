package com.example.rc_6.src.main.setAddress.models

import com.example.rc_6.BuildConfig
import com.example.rc_6.src.main.setAddress.models.kakao.TmCoordinatesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoLocalInterface {

    @Headers("Authorization: KakaoAK ${BuildConfig.KAKAO_API_KEY}")
    @GET("/v2/local/geo/transcoord.json?output_coord=TM")
    fun getTmCoordinates(
        @Query("x") longitude : Double,
        @Query("y") latitude : Double,
    ) : Call<TmCoordinatesResponse>
}