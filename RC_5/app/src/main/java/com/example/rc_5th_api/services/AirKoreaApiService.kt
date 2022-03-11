package com.example.rc_5th_api.services

import com.example.rc_5th_api.BuildConfig
import com.example.rc_5th_api.services.airQuality.AirQualityResponse
import com.example.rc_5th_api.services.monitoringstation.MonitoringStationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirKoreaApiService {


    @GET("B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList"+
            "?serviceKey=${BuildConfig.AIR_KOREA_API_KEY}"+
            "&returnType=json")
    suspend fun getNearbyMonitoringStation(
        @Query("tmX") tmX:Double,
        @Query("tmY") tmY:Double
    ): Response<MonitoringStationsResponse>

    @GET("B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"+
            "?serviceKey=${BuildConfig.AIR_KOREA_API_KEY}"+
            "&returnType=json"+"&dataTerm=DAILY"+
            "&ver=1.3")
    suspend fun getRealTimeAirQualties(
        @Query("stationName") stationName : String
    ) : Response<AirQualityResponse>
}