package com.example.rc_4.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rc_4.CustomAdapter
import com.example.rc_4.HomeRecyclerViewAdapter
import com.example.rc_4.R
import com.example.rc_4.databinding.FragmentHomeBinding
import com.example.rc_4.databinding.FragmentHomeProductBinding

data class homeData(var mall_name:String, var product_name:String, var sale : String, val price:String, val img : Int)
class HomeFragment: Fragment()  {

    private lateinit var binding : FragmentHomeProductBinding
    private lateinit var recyclerViewAdapter: HomeRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeProductBinding.inflate(inflater, container, false)
        binding.rvHomeProduct.layoutManager = LinearLayoutManager(this.requireActivity())
        binding.rvHomeProduct.adapter = recyclerViewAdapter
        recyclerViewAdapter.data = listOf(
            homeData(mall_name = "슬로우앤드", product_name = "[문의폭주!] #SLOWMADE.모먼트으으으으으으으으", sale = "25%", price = "26,100", img = R.drawable.home_preview),
            homeData(mall_name = "하우유", product_name = "너비 반팔 부클 크롭 가디건 (2Color)", sale = "", price = "28,500", img = R.drawable.home_preview2),
            homeData(mall_name = "위드윤", product_name = "베를린 denim shirt", sale = "", price = "32,000", img = R.drawable.home_preview3),
            homeData(mall_name = "라룸", product_name = "[자체제작] 라룸딕유넥티셔츠", sale = "", price = "17,000", img = R.drawable.home_preview4)
        )
//        binding.
//
//        for(x in 0..10){
//
//        }
//        recyclerViewAdapter = HomeRecyclerViewAdapter(this.requireActivity(), shoppingmallCardList)
//        binding.listBookMark.adapter = customAdapter

        return binding.root


    }
}