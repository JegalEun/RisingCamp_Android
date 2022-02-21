package com.example.rc_4

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rc_4.Fragment.BookMarkFragment
import com.example.rc_4.Fragment.RankingFragment
import com.example.rc_4.databinding.ActivityMainBinding
import com.example.rc_4.databinding.ActivityStoreBinding
import com.example.rc_4.databinding.ListviewBookMarkItemBinding
import com.google.android.material.tabs.TabLayout

class StoreActivity : AppCompatActivity() {

    private lateinit var tab_ranking:RankingFragment
    private lateinit var tab_bookMark:BookMarkFragment
    private lateinit var binding: ActivityStoreBinding
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 프래그먼트 객체화
        tab_ranking = RankingFragment()
        tab_bookMark = BookMarkFragment()
        tabLayout = binding.tlStore

        supportFragmentManager.beginTransaction().add(R.id.ll_fragment,tab_ranking).commit()

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        replaceView(tab_ranking)
                    }
                    1 -> {
                        replaceView(tab_bookMark)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private  fun replaceView(tab:Fragment) {
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.ll_fragment, it).commit()
        }
    }


}