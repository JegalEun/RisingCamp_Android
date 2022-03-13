package com.example.rc_6.src.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rc_6.R
import com.example.rc_6.config.BaseFragment
import com.example.rc_6.databinding.FragmentHomeBinding

data class AdArrayList(val position: String,val img: Int)

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    var adArrayList = ArrayList<AdArrayList>()
    private lateinit var ad_viewPager: ViewPagerAdapter
    private lateinit var handler : Handler
    private var currentPosition=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰페이저 바꾸기
        handler = Handler(Looper.getMainLooper()) {
            setPage()
            true
        }

        adArrayList.add(AdArrayList("1",R.drawable.page1))
        adArrayList.add(AdArrayList("2",R.drawable.ad2))
        adArrayList.add(AdArrayList("3",R.drawable.ad3))
        adArrayList.add(AdArrayList("4",R.drawable.page1))
        adArrayList.add(AdArrayList("5",R.drawable.ad2))

        ad_viewPager = ViewPagerAdapter(this.requireActivity(), adArrayList)
        binding.vpAd.adapter = ad_viewPager

        // 뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()

        binding.tvDeliveryAddress.setOnClickListener {
            activity?.let {
                var intent = Intent(context, SetAddressActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root


    }
    fun setPage() {
        if(currentPosition==5) {
            currentPosition=0
        }
        binding.vpAd.setCurrentItem(currentPosition,true)
        currentPosition++
    }
    inner class PagerRunnable : Runnable {
        override fun run() {
            while(true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}