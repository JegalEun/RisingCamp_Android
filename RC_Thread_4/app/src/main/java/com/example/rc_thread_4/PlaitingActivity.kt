package com.example.rc_thread_4

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.rc3rd.CustomDialog
import com.example.rc3rd.SharedPreferencesController
import com.example.rc_thread_4.databinding.ActivityPlaitingBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


private lateinit var binding : ActivityPlaitingBinding
private lateinit var drag_list : HashMap<String, ViewGroup>
private lateinit var image_list : IntArray
private var dish_count =0
var score = 0

class PlaitingActivity : AppCompatActivity() {

    private lateinit var timer : Timer
    private lateinit var textTimer : Timer
    private lateinit var timerTask : TimerTask
    private val IMAGEVIEW_TAG_1 = "만두국"
    private val IMAGEVIEW_TAG_2 = "오믈렛"
    private val IMAGEVIEW_TAG_3 = "만두"
    private val IMAGEVIEW_TAG_4 = "화이트"
    private val IMAGEVIEW_TAG_5 = "만두전골"
    private val IMAGEVIEW_TAG_6 = "유부"
    private val IMAGEVIEW_TAG_7 = "food2"
    private val IMAGEVIEW_TAG_9 = "떡볶이"


    private lateinit var list : ArrayList<Int>
    private lateinit var array_img : IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaitingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        timer = Timer()
        textTimer = Timer()
        TimerTask()
        drag_list = HashMap<String, ViewGroup>()
        image_list = IntArray(9)

        list = intent.getSerializableExtra("list") as ArrayList<Int>
        array_img = intent.getSerializableExtra("array_img") as IntArray

        var handler = Handler(Looper.getMainLooper())
        var isFinish = false

        //음식 드래그
        binding.ivMandukgook.setTag(IMAGEVIEW_TAG_1)
        binding.ivMandukgook.setOnLongClickListener(LongClickListener())
        binding.ivOmelet.setTag(IMAGEVIEW_TAG_2)
        binding.ivOmelet.setOnLongClickListener(LongClickListener())
        binding.ivMandu.setTag(IMAGEVIEW_TAG_3)
        binding.ivMandu.setOnLongClickListener(LongClickListener())
        binding.ivWhiteAndoZzim.setTag(IMAGEVIEW_TAG_4)
        binding.ivWhiteAndoZzim.setOnLongClickListener(LongClickListener())
        binding.ivManduJeongol.setTag(IMAGEVIEW_TAG_5)
        binding.ivManduJeongol.setOnLongClickListener(LongClickListener())
        binding.ivYoobu.setTag(IMAGEVIEW_TAG_6)
        binding.ivYoobu.setOnLongClickListener(LongClickListener())
        binding.ivFood2.setTag(IMAGEVIEW_TAG_7)
        binding.ivFood2.setOnLongClickListener(LongClickListener())
        binding.ivBokki.setTag(IMAGEVIEW_TAG_9)
        binding.ivBokki.setOnLongClickListener(LongClickListener())

        binding.llDish1.setOnDragListener(DragListener())
        binding.llDish2.setOnDragListener(DragListener())
        binding.llDish3.setOnDragListener(DragListener())
        binding.llDish4.setOnDragListener(DragListener())
        binding.llDish5.setOnDragListener(DragListener())
        binding.llDish6.setOnDragListener(DragListener())
        binding.llDish7.setOnDragListener(DragListener())
        binding.llDish8.setOnDragListener(DragListener())

        // next 눌렀을 때 8가지 음식 모두 선택하지 않으면 다음으로 넘어가지 않음
        binding.ivNext.setOnClickListener {
            if(dish_count<8){
                TextTimer()
            }else {
                //채점으로 넘어가기
                timer.cancel()
                binding.tvTimer.isVisible=false
                correct(handler)
                isFinish=true
            }
        }

        if(isFinish){
            result()
        }


    }

    // 이어서 하시겠습니가?
    override fun onRestart() {
        super.onRestart()

        val dialog = CustomDialog(this)
        dialog.showDialog()

        TimerTask()
    }

    override fun onStop() {
        super.onStop()
        timerTask.cancel()
    }

    // " 8개 만두 골라주세요 " 3초만 보여주기
    private fun TextTimer() {
        timerTask = object : TimerTask() {
            var count = 3
            override fun run() {
                binding.tvAlert.post(Runnable {
                    if (count > 0) {
                        binding.tvAlert.isVisible=true
                    } else {
                        binding.tvAlert.isVisible=false
                    }
                    count--
                })
            }
        }
        textTimer.schedule(timerTask, 0, 1000)
    }

    // 타이머
    private fun TimerTask() {
        timerTask = object : TimerTask() {
            var count = 30
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

    // 이미지 클릭 드래그 클래스
    private class LongClickListener : OnLongClickListener {
        override fun onLongClick(view: View): Boolean {

            // 태그 생성
            val item = ClipData.Item(
                view.getTag() as CharSequence
            )
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(
                view.getTag().toString(),
                mimeTypes, item
            )
            val shadowBuilder = View.DragShadowBuilder(
                view
            )
            view.startDragAndDrop(
                data,  // data to be dragged
                shadowBuilder,  // drag shadow
                view,  // 드래그 드랍할  Vew
                0 // 필요없은 플래그
            )
            view.setVisibility(View.INVISIBLE)
            return true
        }
    }

    class DragListener : OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {

            // 이벤트 시작
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    event.clipDescription.hasMimeType((ClipDescription.MIMETYPE_TEXT_PLAIN))
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED")
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    v.invalidate()
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED")
                    // 이미지가 들어왔다는 것
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // 이미지가 해당 영역에서 벗어났다는 것
                    v.invalidate()
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED")
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // 이미지를 드래그하고 놓았을 때
                    ++dish_count
                    Log.d("DragClickListener", "ACTION_DROP")
                    if (v === binding.llDish1) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)

                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[0]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[0]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[0]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[0]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[0]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[0]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[0]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[0]=7;
                        }

                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish2) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)

                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[1]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[1]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[1]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[1]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[1]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[1]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[1]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[1]=7;
                        }

                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish3) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)

                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[2]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[2]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[2]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[2]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[2]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[2]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[2]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[2]=7;
                        }

                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish4) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)

                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[3]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[3]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[3]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[3]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[3]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[3]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[3]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[3]=7;
                        }

                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish5) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)

                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[4]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[4]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[4]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[4]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[4]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[4]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[4]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[4]=7;
                        }

                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish6) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[5]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[5]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[5]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[5]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[5]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[5]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[5]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[5]=7;
                        }
                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish7) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[6]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[6]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[6]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[6]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[6]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[6]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[6]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[6]=7;
                        }
                        view.visibility = View.VISIBLE
                        true
                    }
                    else if (v === binding.llDish8) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        if(view.getTag().toString().equals("만두국")) {
//                            drag_list.put("1", ))
                            image_list[7]=0;
                        }
                        else if(view.getTag().toString().equals("오믈렛")) {
                            image_list[7]=1;
//                            Log.e("제발 ㅠ", view.getTag().toString())
                        }
                        else if(view.getTag().toString().equals("만두")) {
                            image_list[7]=2;
                        }
                        else if(view.getTag().toString().equals("화이트")) {
                            image_list[7]=3;
                        }
                        else if(view.getTag().toString().equals("만두전골")) {
                            image_list[7]=4;
                        }
                        else if(view.getTag().toString().equals("유부")) {
                            image_list[7]=5;
                        }
                        else if(view.getTag().toString().equals("food2")) {
                            image_list[7]=6;
                        }
                        else if(view.getTag().toString().equals("떡볶이")) {
                            image_list[7]=7;
                        }
                        view.visibility = View.VISIBLE
                        true
                    }
                    else {
                        // 드래그가 접시 위에서부터 벗어났을 때 ....
                        val view = event.localState as View
                        view.visibility = View.VISIBLE
                    }
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    v.invalidate()
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED")
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> true
                else -> false
            }
            return true
        }
    }

    // 채점하는 함수
    fun correct(handler: Handler){

        var isFinish = false

        Thread() {

                for (i in 0..7) {
                    Thread.sleep(1000)

                    handler.post {
                        var answer = list[i]
                        Log.e("정답",answer.toString())
                        var drag = image_list[i]
                        Log.e("내가 드래그한 음식", drag.toString())

//                        if(answer.equals(drag)==true) {
////                            binding.tvCorrect.isVisible=true
////                            binding.tvCorrect.setText("맞았습니다.")
//                            score++
//                            binding.tvScore.setText(score.toString())
//                            binding.tvScore.isVisible=true
//                        }
//                        else {
////                            binding.tvCorrect.isVisible=true
////                            binding.tvCorrect.setText("틀렸습니다.")
//                            binding.tvScore.setText(score.toString())
//                            binding.tvScore.isVisible=true
//                        }

                        when(i){
                            0 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish1);
                                    binding.llDish1.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                                else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish1);
                                    binding.ivDish1.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            1 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish2);
                                    binding.ivDish2.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish2);
                                    binding.ivDish2.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            2 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish3);
                                    binding.ivDish3.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish3);
                                    binding.ivDish3.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            3 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish4);
                                    binding.ivDish4.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish4);
                                    binding.ivDish4.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            4 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish5);
                                    binding.ivDish5.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish5);
                                    binding.ivDish5.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            5 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish6);
                                    binding.ivDish6.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish6);
                                    binding.ivDish6.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            6 -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish7);
                                    binding.ivDish7.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish7);
                                    binding.ivDish7.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                            else -> {
                                if(answer.equals(drag)==true){
                                    Glide.with(this).load(R.drawable.okay).into(binding.ivDish8);
                                    binding.ivDish8.isVisible=true
                                    score++
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }else {
                                    Glide.with(this).load(R.drawable.unokay).into(binding.ivDish8);
                                    binding.ivDish8.isVisible=true
                                    binding.tvScore.setText(score.toString())
                                    binding.tvScore.isVisible=true
                                }
                            }
                        }
                    }
            }
        }.start()


        Log.d("점수",score.toString())
        // 점수 저장
        SharedPreferencesController.setScore(this, score.toString())
    }

    // 채점 다 끝나고 다음으로 넘어가는 함수
    fun result(){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
        finish()
    }


}





