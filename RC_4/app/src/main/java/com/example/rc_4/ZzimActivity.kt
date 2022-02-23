package com.example.rc_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rc_4.Adapter.HomeRecyclerViewAdapter
import com.example.rc_4.Adapter.ZzimRecyclerViewAdapter
import com.example.rc_4.Fragment.homeData
import com.example.rc_4.databinding.ActivityZzimPageBinding

data class ZZimData(var mall_name:String, var product_name:String, var sale: String?, val price:String, val img: Int)
class ZzimActivity : AppCompatActivity() {

    private lateinit var binding : ActivityZzimPageBinding
    private lateinit var recyclerViewAdapter: ZzimRecyclerViewAdapter
    private lateinit var homeAdapter: HomeRecyclerViewAdapter
    var dataList = ArrayList<ZZimData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityZzimPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 찜한 데이터 가져오기
        val mall_name : String = intent.getSerializableExtra("mall_name") as String
        val product_name : String = intent.getSerializableExtra("product_name") as String
        val price : String = intent.getSerializableExtra("price") as String
        val img : Int = intent.getSerializableExtra("img") as Int
        val sale : String = intent.getSerializableExtra("sale") as String
//
//        dataList.add(intent.getSerializableExtra("mall_name")as ZZimData)
        dataList.add(ZZimData(mall_name,product_name, sale, price, img))

//        for(x in 0..5){
//            dataList.add(ZZimData("슬로우앤드","최대 [문의폭주!] #SLOWMADE.모먼트으으으으으으으으,000원 할인", "25%", "26,100", R.drawable.home_preview))
//            dataList.add(ZZimData("하우유","너피 반팔 부클 크롭 가디건(2Color)", null, "28,500", R.drawable.home_preview2))
//            dataList.add(ZZimData("위드윤","베를린 denim shirt", null, "32,000", R.drawable.home_preview3))
//            dataList.add(ZZimData("라룸","[자체제작] 라룸딥유넥티셔츠", null, "17,000", R.drawable.home_preview4))
//        }



        // 초기화
        recyclerViewAdapter = ZzimRecyclerViewAdapter(this, dataList)
        recyclerViewAdapter.notifyDataSetChanged()
        // 어댑터 붙이기
        binding.rvZzimPage.adapter = recyclerViewAdapter
        // 레이아웃 설정
        binding.rvZzimPage.layoutManager = GridLayoutManager(this,3)


    }
}