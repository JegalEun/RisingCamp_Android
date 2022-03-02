package com.example.rc_thread_4

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.rc_thread_4.databinding.ActivityPlaitingBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


private lateinit var binding : ActivityPlaitingBinding
private lateinit var drag_list : HashMap<String, Int>
private var dish_count =0

class PlaitingActivity : AppCompatActivity() {

    private lateinit var timer : Timer
    private lateinit var textTimer : Timer
    private lateinit var timerTask : TimerTask
    private val IMAGEVIEW_TAG = "드래그 이미지"
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
        drag_list = HashMap<String, Int>()

        list = intent.getSerializableExtra("list") as ArrayList<Int>
        array_img = intent.getSerializableExtra("array_img") as IntArray


        //음식 드래그
        binding.ivMandukgook.setTag(IMAGEVIEW_TAG)
        binding.ivMandukgook.setOnLongClickListener(LongClickListener())
        binding.ivOmelet.setTag(IMAGEVIEW_TAG)
        binding.ivOmelet.setOnLongClickListener(LongClickListener())
        binding.ivRedManduRect.setTag(IMAGEVIEW_TAG)
        binding.ivRedManduRect.setOnLongClickListener(LongClickListener())
        binding.ivMandu.setTag(IMAGEVIEW_TAG)
        binding.ivMandu.setOnLongClickListener(LongClickListener())
        binding.ivWhiteAndoZzim.setTag(IMAGEVIEW_TAG)
        binding.ivWhiteAndoZzim.setOnLongClickListener(LongClickListener())
        binding.ivManduJeongol.setTag(IMAGEVIEW_TAG)
        binding.ivManduJeongol.setOnLongClickListener(LongClickListener())
        binding.ivYoobu.setTag(IMAGEVIEW_TAG)
        binding.ivYoobu.setOnLongClickListener(LongClickListener())
        binding.ivFood2.setTag(IMAGEVIEW_TAG)
        binding.ivFood2.setOnLongClickListener(LongClickListener())
        binding.ivTrayMandu.setTag(IMAGEVIEW_TAG)
        binding.ivTrayMandu.setOnLongClickListener(LongClickListener())
        binding.ivBokki.setTag(IMAGEVIEW_TAG)
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
                correnct()
                result()
            }
        }


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
                        drag_list.put("1", view.id)
                        Log.e("dd", view.toString())
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish2) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("2", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish3) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("3", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish4) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("4", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish5) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("5", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish6) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("6", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish7) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("7", view.id)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish8) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        drag_list.put("8", view.id)
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
    fun correnct(){
        var dd = array_img[list[0]]
        Log.e("dd",dd.toString())
        var dddd = drag_list.get("1")
        Log.e("dddd", dddd.toString())
//        for(i in 1..8){
//            if(drag_list.get(i.toString())?.equals(array_img[list[i-1]]) == true){
//                binding.tvCorrect.isVisible=true
//                binding.tvCorrect.setText("맞았습니다.")
//            }else {
//                binding.tvCorrect.setText("틀렸습니다.")
//            }
//        }
    }

    // 채점 다 끝나고 다음으로 넘어가는 함수
    fun result(){
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        finish()
    }


}

