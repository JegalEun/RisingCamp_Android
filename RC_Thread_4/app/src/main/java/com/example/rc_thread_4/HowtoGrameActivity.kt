package com.example.rc_thread_4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityHowtoBinding

class HowtoGrameActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHowtoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHowtoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ivStart.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}