package com.example.androidstudiobasic

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // SharedPreference 에 저장하는 방법
        val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
        // mode
        // - MODE_PRIVATE : 생성한 어플리케이션 에서만 사용 가능
        // - MODE_WORLD_READABLE : 다른 application 사용 가능 -> 읽을 수만 있다
        // - MODE_WORLD_WRITEABLE : 다른 application 사용 가능 -> 기록만 가능
        // - MODE_MULTI_PROCESS : 이미 호출되어 사용 중인지 체크
        // - MODE_APPEND : 기존 preference 에 신규로 추가
        val editor: SharedPreferences.Editor = sharedPreference.edit()  //sharedPreference 로부테 에디터 얻어오기
        editor.putString("hello","안녕하세요")
        editor.commit() // 데이터가 들어가게 하기 위한 commit

        // sp1 -> SharedPreferences
        //        (key, value) -> ("hello","안녕하세요")
        // sp2 -> SharedPreferences
        //        (key, value) -> ("hello","안녕하세요11")

        button_pre.setOnClickListener {
            // 값을 불러오기
            val sharedPreferences = getSharedPreferences("sp1", MODE_PRIVATE)
            val value = sharedPreferences.getString("hello","no data")
            Log.d("key-value","Value : "+value)
        }

    }
}