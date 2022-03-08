package com.example.rc_5th_api.services

import com.example.rc_5th_api.BuildConfig
import com.example.rc_5th_api.services.monitoringstation.MonitoringStation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object Repository {

    suspend fun getNearByMonitoringStation(latitude : Double, longtitude: Double) : MonitoringStation? {
        val tmCoordinates = kakaoLocalAPI
            .getTmCoordinates(longtitude, latitude)
            .body()
            ?.documents
            ?.firstOrNull()

        val tmX = tmCoordinates?.x
        val tmY = tmCoordinates?.y

        return airKoreaApiService
            .getNearbyMonitoringStation(tmX!!, tmY!!)
            .body()
            ?.response
            ?.body
            ?.monitoringStations
            ?.minByOrNull { it.tm ?:Double.MAX_VALUE }
    }


    private val kakaoLocalAPI : KakaoLocalAPI by lazy {
        Retrofit.Builder()
            .baseUrl(Url.KAKAO_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildHttpClient())
            .build()
            .create()
    }

    private val airKoreaApiService : AirKoreaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Url.AIR_KOREA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildHttpClient())
            .build()
            .create()
    }

    private fun buildHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if(BuildConfig.DEBUG){
                        HttpLoggingInterceptor.Level.BODY
                    }else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
}
