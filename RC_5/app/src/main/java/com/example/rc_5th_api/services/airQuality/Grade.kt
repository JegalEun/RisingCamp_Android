package com.example.rc_5th_api.services.airQuality

import androidx.annotation.ColorRes
import com.example.rc_5th_api.R
import com.google.gson.annotations.SerializedName

enum class Grade(val label:String, val emoji : String, @ColorRes val colorResId : Int) {
    @SerializedName("1")
    GOOD("μ’μ", "π", R.color.blue),
    @SerializedName("2")
    NORMAL("λ³΄ν΅", "π", R.color.green),
    @SerializedName("3")
    BAD("λμ¨", "βΉοΈ", R.color.yellow),
    @SerializedName("4")
    AWFUL("λ§€μ° λμ¨", "π«", R.color.red),

    UNKNOWN("λ―ΈμΈ‘μ ", "π³", R.color.graygray);

    override fun toString():String {
        return "$label $emoji"
    }
}