package com.example.rc_6.src.main.setAddress

import android.util.Log
import com.example.rc_6.config.ApplicationClass
import com.example.rc_6.src.main.setAddress.models.AirKoreaApiInerface
import com.example.rc_6.src.main.setAddress.models.KakaoLocalInterface
import com.example.rc_6.src.main.setAddress.models.airQuality.AirQualityResponse
import com.example.rc_6.src.main.setAddress.models.kakao.TmCoordinatesResponse
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStation
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetAddressService(val view : SetAddressActivityView) {

    fun tryGetTm(latitude : Double, longtitude: Double) {
        val tmRetrofitInterface = ApplicationClass.sRetrofit.create(KakaoLocalInterface::class.java)
        tmRetrofitInterface.getTmCoordinates(latitude, longtitude).enqueue(object : Callback<TmCoordinatesResponse> {
            override fun onResponse(call: Call<TmCoordinatesResponse>, response: Response<TmCoordinatesResponse>) {
                view.onGetTmSuccess(response.body() as TmCoordinatesResponse)

                Log.d("SetAddressService", response.toString())
            }

            override fun onFailure(call: Call<TmCoordinatesResponse>, t: Throwable) {
                view.onGetTmFailure(t.message ?: "통신 오류")
                Log.d("SetAddressService", t.toString())
            }
        })
    }

    fun tryGetStation(latitude : Double, longtitude: Double){
        val stationRetrofitInterface = ApplicationClass.sRetrofit.create(AirKoreaApiInerface::class.java)
        stationRetrofitInterface.getNearbyMonitoringStation(latitude, longtitude).enqueue(object : Callback<MonitoringStationsResponse> {
            override fun onResponse(call: Call<MonitoringStationsResponse>, response: Response<MonitoringStationsResponse>) {
                view.onGetStationSuccess(response.body() as MonitoringStationsResponse)
            }

            override fun onFailure(call: Call<MonitoringStationsResponse>, t: Throwable) {
                view.onGetStationFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetAirQuality(stationName : String){
        val AirQualityRetrofitInterface = ApplicationClass.sRetrofit.create(AirKoreaApiInerface::class.java)
        AirQualityRetrofitInterface.getRealTimeAirQualties(stationName).enqueue(object : Callback<AirQualityResponse> {
            override fun onResponse(call: Call<AirQualityResponse>, response: Response<AirQualityResponse>) {
                view.onGetAirQualitySuccess(response.body() as AirQualityResponse)
            }

            override fun onFailure(call: Call<AirQualityResponse>, t: Throwable) {
                view.onGetAirQualityFailure(t.message ?: "통신 오류")
            }
        })
    }
}