package com.example.api_practice.src.main.today.now.models

import com.google.gson.annotations.SerializedName

data class BestSellerDto(
    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<Book>
    )