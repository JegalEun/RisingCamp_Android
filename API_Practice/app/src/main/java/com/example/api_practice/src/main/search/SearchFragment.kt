package com.example.api_practice.src.main.search

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.api_practice.R
import com.example.api_practice.config.BaseFragment
import com.example.api_practice.databinding.FragmentSearchBinding
import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.BookData
import com.example.api_practice.src.main.today.now.NowService
import com.example.api_practice.src.main.today.now.SearchFragmentView
import com.example.api_practice.src.main.today.now.SearchService

data class SearchBook(val title : String, val img : String)
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search), SearchFragmentView {

    var dataList = ArrayList<SearchBook>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SearchService(this).tryGetSearchBooks()

    }

    override fun onGetSearchBookSuccess(response: SearchBookDto) {
        for (book in response.books) {
            dataList.add(SearchBook(book.title, book.coverLargeUrl))
            Log.d("BestSeller", book.title)
            Log.d("Url",book.coverSmallUrl)
        }
    }

    override fun onGetSearchBookFailure(message: String) {

    }

}