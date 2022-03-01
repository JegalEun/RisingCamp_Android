package com.example.rc_thread_4

import android.content.Intent
import android.icu.text.TimeZoneFormat
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityPlayBinding
import java.util.*



class PlayActivity : AppCompatActivity() {

    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask
    private lateinit var binding : ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        timer = Timer()

        //타이머
        TimerTask()

        binding.ivNext.setOnClickListener {
            val intent = Intent(this, PlaitingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //  이어서 하시겠습니까?
    override fun onRestart() {
        super.onRestart()
    }

    // Shared에 유저 점수 저장
    override fun onDestroy() {
        super.onDestroy()


    }

    // 타이머
    private fun TimerTask() {
        timerTask = object : TimerTask() {
            var count = 10
            override fun run() {
                binding.tvTimer.post(Runnable {
                    if (count > 0) {
                        binding.tvTimer.setText(count.toString())
                    } else {
                        val intent = Intent(applicationContext, PlaitingActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    count--
                })
            }
        }
        timer.schedule(timerTask, 0, 1000)
    }
}

