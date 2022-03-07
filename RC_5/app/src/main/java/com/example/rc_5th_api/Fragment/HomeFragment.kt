package com.example.rc_5th_api.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rc_5th_api.Adapter.ViewPagerAdapter
import com.example.rc_5th_api.R
import com.example.rc_5th_api.SetAddressActivity
import com.example.rc_5th_api.databinding.FragmentHomeBinding

data class AdArrayList(val position: String, val img: Int)

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    var adArrayList = ArrayList<AdArrayList>()
    private lateinit var ad_viewPager: ViewPagerAdapter
    private lateinit var handler : Handler
    private var currentPosition=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // 뷰페이저 바꾸기
        handler = Handler(Looper.getMainLooper()) {
            setPage()
            true
        }

        adArrayList.add(AdArrayList("1 / 5 모두 보기", R.drawable.page1))
        adArrayList.add(AdArrayList("2 / 5 모두 보기", R.drawable.page1))
        adArrayList.add(AdArrayList("3 / 5 모두 보기", R.drawable.page1))
        adArrayList.add(AdArrayList("4 / 5 모두 보기", R.drawable.page1))
        adArrayList.add(AdArrayList("5 / 5 모두 보기", R.drawable.page1))

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