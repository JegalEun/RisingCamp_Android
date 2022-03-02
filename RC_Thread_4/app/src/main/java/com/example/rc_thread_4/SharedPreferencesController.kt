package com.example.rc3rd

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesController {
    private val user_score : String = "0"

    // 점수 집어넣기
    fun setScore(ctx: Context, score: String){
        val preference : SharedPreferences = ctx.getSharedPreferences(user_score, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = preference.edit()
        editor.putString(user_score, score.toString())
        editor.commit()
    }

    // 점수 가져오기
    fun getScore(ctx:Context) : String? {
        val preference : SharedPreferences = ctx.getSharedPreferences(user_score, Context.MODE_PRIVATE)
        return preference.getString(user_score, "")        // 키 이름, 값이 없을 때 리턴할 값
    }

}
