package com.example.rc3rd

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Button
import com.example.rc_thread_4.MainActivity
import com.example.rc_thread_4.R
import com.example.rc_thread_4.databinding.ActivityIngBinding


class CustomDialog(context: Context) {

    private val dialog = Dialog(context)
    private lateinit var binding : ActivityIngBinding
    private val mContext: Context? = null

    fun showDialog(){
        dialog.setContentView(R.layout.activity_ing)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val btn_yes = dialog.findViewById<Button>(R.id.btn_yes)
        val btn_no = dialog.findViewById<Button>(R.id.btn_no)

        btn_yes.setOnClickListener {
            dialog.dismiss()
        }

        btn_no.setOnClickListener {
            val intent = Intent(mContext, MainActivity::class.java)
            mContext!!.startActivity(intent)
            dialog.dismiss()
        }


    }

}
