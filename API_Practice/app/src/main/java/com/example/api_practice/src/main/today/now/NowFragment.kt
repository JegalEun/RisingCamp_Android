package com.example.api_practice.src.main.today.now

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_practice.R
import com.example.api_practice.config.BaseFragment
import com.example.api_practice.databinding.FragmentFragmentNowBinding
import com.example.api_practice.src.main.search.models.SearchBookDto
import com.example.api_practice.src.main.today.now.models.BestSellerDto

data class BookData(var booktitle : String)

class NowFragment : BaseFragment<FragmentFragmentNowBinding>(FragmentFragmentNowBinding::bind, R.layout.fragment_fragment_now)
, NowFragmentView {

    private lateinit var recyclerViewAdapter: NowRecyclerViewAdapter
    var dataList = ArrayList<BookData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList.add(BookData("책"))
        dataList.add(BookData("책"))
        dataList.add(BookData("책"))
        dataList.add(BookData("책"))
        dataList.add(BookData("책"))

        recyclerViewAdapter = NowRecyclerViewAdapter(this.requireActivity(), dataList)
        binding.rvBookList.adapter = recyclerViewAdapter

        NowService(this).tryGetBestSellerBooks()

        isEmpty()
    }
    // 리사이클러뷰의 아이템이 0개일 때
    fun isEmpty(){
        if(recyclerViewAdapter.itemCount==0){
            binding.rvBookList.visibility=View.GONE
            binding.tvBookEmpty.visibility=View.VISIBLE
        }
    }

    override fun onGetBestSellerSuccess(response: BestSellerDto) {
        TODO("Not yet implemented")
    }

    override fun onGetBestSellerFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetSearchBookSuccess(response: SearchBookDto) {
        TODO("Not yet implemented")
    }

    override fun onGetSearchBookFailure(message: String) {
        TODO("Not yet implemented")
    }
}