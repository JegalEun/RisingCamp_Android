package com.example.rc_6.src.main.setAddress.models

import com.example.rc_6.BuildConfig
import com.example.rc_6.src.main.setAddress.models.airQuality.AirQualityResponse
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStationsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirKoreaApiInerface {


    @GET("B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList"+
            "?serviceKey=${BuildConfig.AIR_KOREA_API_KEY}"+
            "&returnType=json")
    fun getNearbyMonitoringStation(
        @Query("tmX") tmX:Double,
        @Query("tmY") tmY:Double
    ): Call<MonitoringStationsResponse>

    @GET("B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"+
            "?serviceKey=${BuildConfig.AIR_KOREA_API_KEY}"+
            "&returnType=json"+"&dataTerm=DAILY"+
            "&ver=1.3")
    fun getRealTimeAirQualties(
        @Query("stationName") stationName : String
    ) : Call<AirQualityResponse>
}