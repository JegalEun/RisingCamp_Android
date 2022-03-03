package com.example.rc_thread_4

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.example.rc3rd.CustomDialog
import com.example.rc_thread_4.databinding.ActivityPlayBinding
import java.util.*

class PlayActivity : AppCompatActivity() {

    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask
    private lateinit var binding : ActivityPlayBinding
    private val list = arrayListOf(0,1,2,3,4,5,6,7,8)
    var count : Int = 0

    var images = intArrayOf(
        R.drawable.mandukgook,
        R.drawable.omelet,
        R.drawable.mandu,
        R.drawable.white_ando_zzim,
        R.drawable.mandu_jeongol,
        R.drawable.yoobu,
        R.drawable.food2,
        R.drawable.tray_mandu,
        R.drawable.bokki
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        timer = Timer()

        //타이머
        TimerTask()
        // 사진배열 랜덤하게
        list.shuffle()
        setImage()

        binding.ivNext.setOnClickListener {
            var ddd = images[list[0]]
            val intent = Intent(this, PlaitingActivity::class.java)
            intent.putExtra("list",list)
            intent.putExtra("array_img",images)
            startActivity(intent)
            finish()
        }
    }

    // 이미지 랜덤으로 배치
    fun setImage(){

        val mImageView: ImageView = findViewById<View>(R.id.iv_food1) as ImageView
        val imageId = list[0]
        mImageView.setImageResource(images[imageId])
//        var dt = images[imageId].
//        Log.d("dttdt",dt.toString())
        Log.d("imageId",imageId.toString())
        val mImageView2: ImageView = findViewById<View>(R.id.iv_food2) as ImageView
        val imageId2 = list[1]
        Log.d("imageId",imageId2.toString())
        mImageView2.setImageResource(images[imageId2])
        val mImageView3: ImageView = findViewById<View>(R.id.iv_food3) as ImageView
        val imageId3 = list[2]
        Log.d("imageId",imageId3.toString())
        mImageView3.setImageResource(images[imageId3])
        val mImageView4: ImageView = findViewById<View>(R.id.iv_food4) as ImageView
        val imageId4 = list[3]
        Log.d("imageId",imageId4.toString())
        mImageView4.setImageResource(images[imageId4])
        val mImageView5: ImageView = findViewById<View>(R.id.iv_food5) as ImageView
        val imageId5 = list[4]
        Log.d("imageId",imageId5.toString())
        mImageView5.setImageResource(images[imageId5])
        val mImageView6: ImageView = findViewById<View>(R.id.iv_food6) as ImageView
        val imageId6 = list[5]
        Log.d("imageId",imageId6.toString())
        mImageView6.setImageResource(images[imageId6])
        val mImageView7: ImageView = findViewById<View>(R.id.iv_food7) as ImageView
        val imageId7 = list[6]
        Log.d("imageId",imageId7.toString())
        mImageView7.setImageResource(images[imageId7])
        val mImageView8: ImageView = findViewById<View>(R.id.iv_food8) as ImageView
        val imageId8 = list[7]
        Log.d("imageId",imageId8.toString())
        mImageView8.setImageResource(images[imageId8])
    }

    //  이어서 하시겠습니까?
    override fun onRestart() {
        super.onRestart()

        val dialog = CustomDialog(this)
        dialog.showDialog()

        TimerTask()

    }

    override fun onPause() {
        super.onPause()

        timerTask.cancel()

    }

    override fun onStop() {
        super.onStop()
        timerTask.cancel()
    }

    // 타이머
    private fun TimerTask() {
        timerTask = object : TimerTask() {
            var count=10
            override fun run() {
                binding.tvTimer.post(Runnable {
                    if (count > 0) {
                        binding.tvTimer.setText(count.toString())
                    } else {
                        val intent = Intent(applicationContext, PlaitingActivity::class.java)
                        intent.putExtra("list",list)
                        intent.putExtra("array_img",images)
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

