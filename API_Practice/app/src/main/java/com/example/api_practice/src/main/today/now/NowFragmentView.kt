package com.example.api_practice.src.main.today.now

import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.models.BestSellerDto

interface NowFragmentView {

    fun onGetBestSellerSuccess(response: BestSellerDto)

    fun onGetBestSellerFailure(message: String)

    fun onGetSearchBookSuccess(response: SearchBookDto)

    fun onGetSearchBookFailure(message: String)
}