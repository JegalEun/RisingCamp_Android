package com.example.rc_thread_4

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        var all = intent.getSerializableExtra("all") as Boolean

        Log.d("score", score.toString())
        Log.d("all", all.toString())

        val list = ArrayList<Int>()
        list.add(R.drawable.finish_one)
        list.add(R.drawable.finish_two)

        if(!all){
            // 다맞지 않았다
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
        }

        else {
            // 다 맞았다면
            binding.ivFinish.setImageResource(R.drawable.why)
            binding.tvScore.isVisible=true
        }

        // sharedPreference에서 점수 가져오기
        getScore()

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

    fun getScore(){
        // 저장된 점수가 있다면 가져오기
        if(SharedPreferencesController.getScore(this).isNullOrEmpty()==false){
            val score_ing: String? = SharedPreferencesController.getScore(this)
            val score_int = score_ing?.toInt()
            if (score_ing != null) {
                if (score_int != null) {
                    score = score_int+score
                    SharedPreferencesController.setScore(this, score.toString())
                }
            }
            binding.tvScore.setText("누적 점수 : "+score)
        }else {
            // 저장된 점수가 없다면
            binding.tvScore.setText("누적 점수 : "+score)
            SharedPreferencesController.setScore(this, score.toString())
        }
    }

}
