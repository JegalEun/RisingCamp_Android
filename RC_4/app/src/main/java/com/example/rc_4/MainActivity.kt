package com.example.rc_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rc_4.Fragment.*
import com.example.rc_4.databinding.ActivityMainBinding
import com.example.rc_4.databinding.ActivityStoreBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
//    private lateinit var tab_home: HomeFragment
//    private lateinit var tab_brand: BrandFragment
//    private lateinit var tab_best: BestFragment
//    private lateinit var tab_sale: SaleFragment
//    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBar()

        // 프래그먼트 객체화
//        tab_home = HomeFragment()
//        tab_brand = BrandFragment()
//        tab_best = BestFragment()
//        tab_sale = SaleFragment()

//        tabLayout = binding.tl_home
//
//        supportFragmentManager.beginTransaction().add(R.id.ll_fragment,tab_ranking).commit()
//
//        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when (tab?.position){
//                    0 -> {
//                        replaceView(tab_ranking)
//                    }
//                    1 -> {
//                        replaceView(tab_bookMark)
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })
    }

//    private  fun replaceView(tab:Fragment) {
//        var selectedFragment : Fragment? = null
//        selectedFragment = tab
//        selectedFragment?.let{
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.ll_fragment, it).commit()
//        }
//    }


    fun initNavigationBar(){
        binding.bottomNavi.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
                        changeFragement(HomeFragment())
                    }
                }
                true
            }
            selectedItemId=R.id.home
        }
    }

    fun changeFragement(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flMain.id,fragment).commit()
    }

}