package com.example.rc_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rc_4.Fragment.BookMarkFragment
import com.example.rc_4.Fragment.HomeFragment
import com.example.rc_4.Fragment.RankingFragment
import com.example.rc_4.databinding.ActivityHomeBinding
import com.example.rc_4.databinding.ActivityStoreBinding
import com.google.android.material.tabs.TabLayout

class HomeActivity: AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    private lateinit var tab_home: HomeFragment
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tab_home = HomeFragment()
        tabLayout = binding.tlHome

        supportFragmentManager.beginTransaction().add(R.id.ll_fragment,tab_home).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> {
                        replaceView(tab_home)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private  fun replaceView(tab: Fragment) {
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.ll_fragment, it).commit()
        }
    }
}