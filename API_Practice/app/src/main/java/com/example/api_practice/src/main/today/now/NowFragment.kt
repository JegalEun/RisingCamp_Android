package com.example.api_practice.src.main.today.now

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api_practice.R
import com.example.api_practice.config.BaseFragment
import com.example.api_practice.databinding.FragmentFragmentNowBinding
import com.example.api_practice.src.MainActivity
import com.example.api_practice.src.main.search.NowRecyclerViewAdapter
import com.example.api_practice.src.main.search.SearchBookAdapter
import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.models.BestSellerDto


data class BookData(var booktitle : String, var img : String)

class NowFragment : BaseFragment<FragmentFragmentNowBinding>(FragmentFragmentNowBinding::bind, R.layout.fragment_fragment_now)
, NowFragmentView {

    private lateinit var recyclerViewAdapter: NowRecyclerViewAdapter
    var dataList = ArrayList<BookData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recyclerViewAdapter = NowRecyclerViewAdapter(this.requireActivity(), dataList)
//        binding.rvBookList.adapter = recyclerViewAdapter
//        initBestSellerRecycelrView()
        Handler(Looper.getMainLooper()).postDelayed({
            initBestSellerRecycelrView()
            isEmpty()
        }, 1500)

        NowService(this).tryGetBestSellerBooks()

    }
    // 리사이클러뷰의 아이템이 0개일 때
    fun isEmpty(){
        if(recyclerViewAdapter.itemCount==0){
            binding.rvBookList.visibility=View.GONE
            binding.tvBookEmpty.visibility=View.VISIBLE
        }
    }

    override fun onGetBestSellerSuccess(response: BestSellerDto) {

        for (book in response.books) {
            dataList.add(BookData(book.title, book.coverLargeUrl))
            Log.d("BestSeller", book.title)
            Log.d("Url",book.coverSmallUrl)
        }

        Log.d("???", dataList.toString())
    }

    override fun onGetBestSellerFailure(message: String) {
    }

    override fun onGetSearchBookSuccess(response: SearchBookDto) {
    }

    override fun onGetSearchBookFailure(message: String) {
    }

    fun initBestSellerRecycelrView() {
        recyclerViewAdapter = NowRecyclerViewAdapter(dataList)
        Log.d("dkdkdk",dataList.toString())
        binding.rvBookList.adapter = recyclerViewAdapter
        binding.rvBookList.layoutManager =
            GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false)
    }
}

