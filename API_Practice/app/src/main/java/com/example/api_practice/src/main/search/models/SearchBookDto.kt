package com.example.api_practice.src.main.search.models

import com.example.api_practice.src.main.today.now.models.Book
import com.google.gson.annotations.SerializedName

data class SearchBookDto (
    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<Book>
        )