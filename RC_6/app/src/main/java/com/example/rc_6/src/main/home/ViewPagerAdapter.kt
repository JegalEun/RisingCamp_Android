package com.example.rc_6.src.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.rc_6.databinding.ViewPageOneBinding

class ViewPagerAdapter(context: Context, private val adArrayList: ArrayList<AdArrayList>) : PagerAdapter() {

    private var mContext: Context?=null
    private lateinit var binding : ViewPageOneBinding
    private val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val inflater = LayoutInflater.from(container.context)
//        val view = inflater.inflate(R.layout.view_page_one, container, false)
//        view.iv_ad_viewpager.

        binding = ViewPageOneBinding.inflate(inflater, container, false)
        binding.ivAdViewpager.setImageResource(adArrayList[position].img)
        binding.tvAdViewpager.setText(adArrayList[position].position+" / "+adArrayList.size+" 모두 보기")
//        val view= LayoutInflater.from(container.context).inflate(R.layout.view_page_one,container,false)
        container.addView(binding.root)
        return binding.root
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return adArrayList.size
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}
