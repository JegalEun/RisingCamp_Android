package com.example.api_practice.src.main.today

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.api_practice.R
import com.example.api_practice.config.BaseFragment
import com.example.api_practice.databinding.FragmentTodayBinding
import com.example.api_practice.src.main.today.audio.AudioFragment
import com.example.api_practice.src.main.today.now.NowFragment
import com.example.api_practice.src.main.today.story.StoryFragment
import com.google.android.material.tabs.TabLayout

class TodayFragment : BaseFragment<FragmentTodayBinding>(FragmentTodayBinding::bind, R.layout.fragment_today) {

    private lateinit var tab_now : NowFragment
    private lateinit var tab_audio : AudioFragment
    private lateinit var tab_story : StoryFragment
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab_now = NowFragment()
        tab_audio = AudioFragment()
        tab_story = StoryFragment()
        tabLayout = binding.tlHome

        childFragmentManager.beginTransaction().add(R.id.ll_fragment_now_book, tab_now).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        replaceView(tab_now)
                    }
                    1->{
                        replaceView(tab_audio)
                    }
                    2-> {
                        replaceView(tab_story)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

    }

    private fun replaceView(tab:Fragment){
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            childFragmentManager.beginTransaction()
                .replace(R.id.ll_fragment_now_book, it).commit()
        }
    }
}