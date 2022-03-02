package com.example.rc_thread_4

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityFinishBinding
import com.example.rc_thread_4.databinding.ActivityPlayBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFinishBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        var handler = Handler(Looper.getMainLooper())

        val list = ArrayList<Int>()
        list.add(R.drawable.finish_one)
        list.add(R.drawable.finish_two)

        Thread() {

            while (true) {
                for (i in list) {
                    Thread.sleep(300)
                    handler.post {
                        binding.ivFinish.setImageResource(i)
                    }
                }
            }
        }.start()

        binding.ivReplay.setOnClickListener {
            Log.d("으잉","왜?")
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}