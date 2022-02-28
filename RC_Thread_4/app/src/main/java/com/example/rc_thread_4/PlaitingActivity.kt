package com.example.rc_thread_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityPlaitingBinding

class PlaitingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlaitingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaitingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}