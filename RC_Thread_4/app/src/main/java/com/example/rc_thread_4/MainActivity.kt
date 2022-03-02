package com.example.rc_thread_4

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var isRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var on = true
        // 음악 실행
        ServiceStart()

        binding.ivMusicOnoff.setOnClickListener {
            if(on){
                ServiceStop()
                on=false
            }else {
                on=true
                ServiceStart()
            }
        }
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

    fun ServiceStart(){
        val intent = Intent(this, MusicService::class.java)
        startService(intent)
    }

    fun ServiceStop(){
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }

}
