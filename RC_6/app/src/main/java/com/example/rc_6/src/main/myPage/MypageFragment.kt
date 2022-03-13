package com.example.rc_6.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.rc_6.R
import com.example.rc_6.config.BaseFragment
import com.example.rc_6.databinding.FragmentMypageBinding
import com.example.rc_6.src.main.Login.LoginActivity

class MypageFragment : BaseFragment<FragmentMypageBinding> (FragmentMypageBinding::bind, R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            activity?.let {
                var intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}