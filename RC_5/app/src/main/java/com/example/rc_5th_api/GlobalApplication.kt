package com.example.rc_5th_api

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "cb9943de8f9e1db1922bc54569ade976")
    }
}