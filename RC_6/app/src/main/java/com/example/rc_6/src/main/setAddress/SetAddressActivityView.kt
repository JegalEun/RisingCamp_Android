package com.example.rc_6.src.main.setAddress

import com.example.rc_6.src.main.setAddress.models.airQuality.AirQualityResponse
import com.example.rc_6.src.main.setAddress.models.kakao.TmCoordinatesResponse
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStation
import com.example.rc_6.src.main.setAddress.models.monitoringStation.MonitoringStationsResponse

interface SetAddressActivityView {

    fun onGetTmSuccess(response: TmCoordinatesResponse)

    fun onGetTmFailure(message: String)

    fun onGetStationSuccess(response: MonitoringStationsResponse)

    fun onGetStationFailure(message: String)

    fun onGetAirQualitySuccess(response: AirQualityResponse)

    fun onGetAirQualityFailure(message: String)

}