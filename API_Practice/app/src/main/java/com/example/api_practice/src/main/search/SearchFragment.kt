package com.example.api_practice.src.main.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var bookAdapter : SearchBookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 검색했을 때 (엔터키를 눌렀을 때)
        binding.etSearchBook.setOnKeyListener { v, keyCode, event ->
            if(keyCode==KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN){
                search(binding.etSearchBook.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun search(keyword: String){
        SearchService(this).tryGetSearchBooks(keyword)

        Handler(Looper.getMainLooper()).postDelayed({
            initSearchBooksRecycelrView()
            isEmpty()
        }, 1000)

    }

    fun isEmpty(){
        if(bookAdapter.itemCount==0){
            binding.rvBookList.visibility=View.GONE
            binding.tvBookList.visibility=View.VISIBLE
        }
    }

    fun initSearchBooksRecycelrView() {
        bookAdapter = SearchBookAdapter(dataList)
        Log.d("dkdkdk",dataList.toString())
        binding.rvBookList.adapter = bookAdapter
        binding.rvBookList.layoutManager = GridLayoutManager(this.requireActivity(),3)
    }


    override fun onGetSearchBookSuccess(response: SearchBookDto) {
        for (book in response.books) {
            dataList.add(SearchBook(book.title, book.coverLargeUrl))
            Log.d("bookSearch", book.title)
        }
    }

    override fun onGetSearchBookFailure(message: String) {

    }

}
