package com.example.rc_5th_api.services


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int?
)