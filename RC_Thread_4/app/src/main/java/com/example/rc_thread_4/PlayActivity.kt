package com.example.rc_thread_4

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rc_thread_4.databinding.ActivityPlayBinding
import java.util.*


data class DishData(val img: Int, val checked: Boolean)
class PlayActivity : AppCompatActivity() {

    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask
    private lateinit var binding : ActivityPlayBinding
    private val dataList = arrayListOf<DishData>()

    // 음식 이미지 배열
    var images = intArrayOf(
        R.drawable.mandukgook,
        R.drawable.omelet,
        R.drawable.red_mandu_rect,
        R.drawable.mandu,
        R.drawable.white_ando_zzim,
        R.drawable.mandu_jeongol,
        R.drawable.yoobu,
        R.drawable.food,
        R.drawable.food2,
        R.drawable.tray_mandu,
        R.drawable.bokki,
        R.drawable.red_mandu,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        timer = Timer()

        //타이머
        TimerTask()
        // 음식 사진 넣기
        setImage()

        binding.ivNext.setOnClickListener {
            val intent = Intent(this, PlaitingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    // 이미지 랜덤으로 배치
    fun setImage(){
//        dataList.add(DishData(R.drawable.mandukgook, false))
//        dataList.add(DishData(R.drawable.omelet, false))
//        dataList.add(DishData(R.drawable.red_mandu_rect, false))
//        dataList.add(DishData(R.drawable.mandu, false))
//        dataList.add(DishData(R.drawable.white_ando_zzim, false))
//        dataList.add(DishData(R.drawable.mandu_jeongol, false))
//        dataList.add(DishData(R.drawable.yoobu, false))
//        dataList.add(DishData(R.drawable.food, false))
//        dataList.add(DishData(R.drawable.food2, false))
//        dataList.add(DishData(R.drawable.bokki, false))
//        dataList.add(DishData(R.drawable.tray_mandu, false))
//        dataList.add(DishData(R.drawable.red_mandu, false))

        val mImageView: ImageView = findViewById<View>(R.id.iv_food1) as ImageView
        val imageId = (Math.random() * images.size)
        mImageView.setBackgroundResource(images[imageId.toInt()])
        val mImageView2: ImageView = findViewById<View>(R.id.iv_food2) as ImageView
        val imageId2 = (Math.random() * images.size)
        mImageView2.setBackgroundResource(images[imageId2.toInt()])
        val mImageView3: ImageView = findViewById<View>(R.id.iv_food3) as ImageView
        val imageId3 = (Math.random() * images.size)
        mImageView3.setBackgroundResource(images[imageId3.toInt()])
        val mImageView4: ImageView = findViewById<View>(R.id.iv_food4) as ImageView
        val imageId4 = (Math.random() * images.size)
        mImageView4.setBackgroundResource(images[imageId4.toInt()])
        val mImageView5: ImageView = findViewById<View>(R.id.iv_food5) as ImageView
        val imageId5 = (Math.random() * images.size)
        mImageView5.setBackgroundResource(images[imageId5.toInt()])
        val mImageView6: ImageView = findViewById<View>(R.id.iv_food6) as ImageView
        val imageId6 = (Math.random() * images.size)
        mImageView6.setBackgroundResource(images[imageId6.toInt()])
        val mImageView7: ImageView = findViewById<View>(R.id.iv_food7) as ImageView
        val imageId7 = (Math.random() * images.size)
        mImageView7.setBackgroundResource(images[imageId7.toInt()])
        val mImageView8: ImageView = findViewById<View>(R.id.iv_food8) as ImageView
        val imageId8 = (Math.random() * images.size)
        mImageView8.setBackgroundResource(images[imageId8.toInt()])
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

