package com.example.rc_thread_4

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rc3rd.SharedPreferencesController
import com.example.rc_thread_4.databinding.ActivityFinishBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFinishBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        var handler = Handler(Looper.getMainLooper())

        // 점수 가져오기
        var score = intent.getSerializableExtra("score") as Int

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

        // 저장된 점수가 있다면 가져오기
        if(SharedPreferencesController.getScore(this).toString().equals(0)){
            val score_ing: String? = SharedPreferencesController.getScore(this)
            val score_int = score_ing?.toInt()
            if (score_ing != null) {
                if (score_int != null) {
                    score = score_int+score
                }
            }
            binding.tvScore.setText("점수 : "+score)
        }else {
            // 저장된 점수가 없다면
            binding.tvScore.setText("점수 : "+score)
        }

        binding.ivReplay.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 점수 SharedPreferences에 저장
        SharedPreferencesController.setScore(this, score.toString())
    }

}
