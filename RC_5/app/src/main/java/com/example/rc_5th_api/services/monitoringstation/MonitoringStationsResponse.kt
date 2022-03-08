package com.example.rc_5th_api.services.monitoringstation


import com.google.gson.annotations.SerializedName

data class MonitoringStationsResponse(
    @SerializedName("response")
    val response: Response?
)