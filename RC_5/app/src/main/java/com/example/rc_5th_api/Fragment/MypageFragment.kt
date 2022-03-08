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
import com.example.rc_5th_api.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    private lateinit var binding : FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMypageBinding.inflate(inflater, container, false)

        return binding.root


    }
}