package com.example.rc_5th_api.services

import com.example.rc_5th_api.BuildConfig
import com.example.rc_5th_api.services.monitoringstation.MonitoringStationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirKoreaApiService {


    @GET("B552584/MsrstnInfoInqireSvc/getMsrstnList"+"?serviceKey=${BuildConfig.AIR_KOREA_API_KEY}"+"&returnType=json")
    suspend fun getNearbyMonitoringStation(
        @Query("tmX") tmX:Double,
        @Query("tmY") tmY:Double
    ): Response<MonitoringStationsResponse>
}