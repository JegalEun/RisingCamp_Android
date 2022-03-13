package com.example.rc_6.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rc_6.R
import com.example.rc_6.config.BaseActivity
import com.example.rc_6.databinding.ActivityMainBinding
import com.example.rc_6.src.main.myPage.MypageFragment
import com.example.rc_6.src.main.order.OrderFragment
import com.example.rc_6.src.main.search.SearchFragment
import com.example.rc_6.src.main.zzim.ZzimFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigationBar()
    }

    fun initNavigationBar() {
        binding.bottomNavi.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
                        changeFragment(HomeFragment())
                    }
                    R.id.zzim -> {
                        changeFragment(ZzimFragment())
                    }
                    R.id.search -> {
                        changeFragment(SearchFragment())
                    }
                    R.id.order -> {
                        changeFragment(OrderFragment())
                    }
                    R.id.mypage -> {
                        changeFragment(MypageFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }

    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.flHome.id, fragment).commit()
    }
}