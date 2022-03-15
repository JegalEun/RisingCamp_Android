package com.example.api_practice.src.main.today.now

import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.models.BestSellerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookInterface {

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=101")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>
}