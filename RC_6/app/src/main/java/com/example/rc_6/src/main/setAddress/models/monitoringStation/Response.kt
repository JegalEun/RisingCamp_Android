package com.example.rc_6.src.main.setAddress.models.monitoringStation

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("body")
    val body: Body?,
    @SerializedName("header")
    val header: Header?
)