package com.example.rc_4.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rc_4.Adapter.HomeRecyclerViewAdapter
import com.example.rc_4.R
import com.example.rc_4.databinding.FragmentHomeBinding

data class homeData(var mall_name:String, var product_name:String, var sale: String?, val price:String, val img: Int)
data class UserCheckBoxStatus(val position : Int, var isChecked:Boolean)
class HomeFragment: Fragment()  {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var recyclerViewAdapter: HomeRecyclerViewAdapter
    var dataList = ArrayList<homeData>()
    var userCheckBoxStatus = ArrayList<UserCheckBoxStatus>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        for(x in 0..10){
            dataList.add(homeData("슬로우앤드","최대 [문의폭주!] #SLOWMADE.모먼트으으으으으으으으,000원 할인", "25%", "26,100", R.drawable.home_preview))
            dataList.add(homeData("하우유","너피 반팔 부클 크롭 가디건(2Color)", "", "28,500", R.drawable.home_preview2))
            dataList.add(homeData("위드윤","베를린 denim shirt", "", "32,000", R.drawable.home_preview3))
            dataList.add(homeData("라룸","[자체제작] 라룸딥유넥티셔츠", "", "17,000", R.drawable.home_preview4))
        }

        // 초기화
        recyclerViewAdapter = HomeRecyclerViewAdapter(this.requireActivity(), dataList)
        // 어댑터 붙이기
        binding.rvHomeProduct.adapter = recyclerViewAdapter
        // 레이아웃 설정
        binding.rvHomeProduct.layoutManager = GridLayoutManager(this.requireActivity(),2)

        return binding.root


    }
}