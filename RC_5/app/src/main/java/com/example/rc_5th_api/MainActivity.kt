package com.example.rc_5th_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rc_5th_api.Fragment.*
import com.example.rc_5th_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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