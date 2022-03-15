package com.example.api_practice.src.main.today.now

import android.util.Log
import com.example.api_practice.config.ApplicationClass
import com.example.api_practice.src.main.today.now.models.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowService(val view: NowFragmentView) {

    fun tryGetBestSellerBooks(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(BookInterface::class.java)
        homeRetrofitInterface.getBestSellerBooks("7703A01321B66BCE78A4AE11E5C4B9CBD9600091F1C3DB5A9446E6EB42B238B0").enqueue(object : Callback<BestSellerDto> {
            override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                view.onGetBestSellerSuccess(response.body() as BestSellerDto)
            }

            override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                view.onGetBestSellerFailure(t.message ?: "통신 오류")
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}