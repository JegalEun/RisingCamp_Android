package com.example.api_practice.src.main.today.now

import android.util.Log
import com.example.api_practice.config.ApplicationClass
import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.models.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val view: SearchFragmentView) {

    fun tryGetSearchBooks(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(BookInterface::class.java)
        homeRetrofitInterface.getBooksByName("7703A01321B66BCE78A4AE11E5C4B9CBD9600091F1C3DB5A9446E6EB42B238B0","안드로이드").enqueue(object : Callback<SearchBookDto> {
            override fun onResponse(call: Call<SearchBookDto>, response: Response<SearchBookDto>) {
                view.onGetSearchBookSuccess(response.body() as SearchBookDto)

                if(!response.isSuccessful.not()){
                    return
                }

                response.body()?.let{
                    Log.d(TAG, it.toString())

                    it.books.forEach { book ->
                        Log.d(TAG, book.toString())
                    }
                }
            }

            override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                view.onGetSearchBookFailure(t.message ?: "통신 오류")
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}