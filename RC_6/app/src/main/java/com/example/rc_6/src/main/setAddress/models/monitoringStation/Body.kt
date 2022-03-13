package com.example.rc_6.src.main.setAddress.models.monitoringStation

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("monitoringStations")
    val monitoringStations: List<MonitoringStation>?,
    @SerializedName("numOfRows")
    val numOfRows: Int?,
    @SerializedName("pageNo")
    val pageNo: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?
)