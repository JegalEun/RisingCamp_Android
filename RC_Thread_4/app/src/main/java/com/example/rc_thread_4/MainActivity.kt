package com.example.rc_thread_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.rc_thread_4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var isRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //how to play 눌렀을 때
        binding.ivHowtogame.setOnClickListener {
            val intent = Intent(this, HowtoGrameActivity::class.java)
            startActivity(intent)
            finish()
        }

        // start 눌렀을 때
        binding.ivStart.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
