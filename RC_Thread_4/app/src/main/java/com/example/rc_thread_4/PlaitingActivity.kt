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
import com.example.rc_thread_4.databinding.ActivityPlaitingBinding
import java.util.*


private lateinit var binding : ActivityPlaitingBinding

class PlaitingActivity : AppCompatActivity() {


    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask
    private val IMAGEVIEW_TAG = "드래그 이미지"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaitingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        timer = Timer()
        TimerTask()

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

    // 이미지 클릭 드래그 클래
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

    internal class DragListener : OnDragListener {

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
                    Log.d("DragClickListener", "ACTION_DROP")
                    if (v === binding.llDish1) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish2) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish3) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish4) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish5) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish6) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish7) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                        true
                    }
                    if (v === binding.llDish8) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
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

//    // 터치 리스너
//    override fun onTouch(view: View, motionEvent : MotionEvent): Boolean {
//        return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
//            val dragShadowBuilder = View.DragShadowBuilder(view)
//            view.startDrag(null,dragShadowBuilder, view, 0)
//            true
//        }else {
//            false
//        }
//    }
//
//    override fun onDrag(p0: View?, p1: DragEvent?): Boolean {
//        if (p1 != null) {
//            when(p1.action) {
//                DragEvent.ACTION_DROP -> {
//                    val iv_1 = p1.localState as View
//                    val iv_2 = iv_1.parent as ViewGroup
//
//                }
//            }
//        }
//    }



}

