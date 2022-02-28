package com.example.rc_thread_4

import android.content.Intent
import android.icu.text.TimeZoneFormat
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityPlayBinding
import java.util.*


private var time = 10
private var inRunning = false
private lateinit var binding : ActivityPlayBinding

class PlayActivity : AppCompatActivity() {


//    private lateinit var timer : TimeHandler
    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        timer = Timer()

        //타이머
        TimerTask()

    }

    //  이어서 하시겠습니까?
    override fun onRestart() {
        super.onRestart()
        inRunning = true
    }

    // Shared에 유저 점수 저장
    override fun onDestroy() {
        super.onDestroy()


    }

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

// 타이머 클래스
class TimeHandler : Handler() {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

        if(time==0){
            binding.tvTimer.setText(time)
            removeMessages(0)
        }
        binding.tvTimer.setText(time--)
        sendEmptyMessageDelayed(0, 1000)
    }
}
