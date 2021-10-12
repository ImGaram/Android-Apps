package com.example.androidpersonalproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Intent2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent2)

        // 이 활동을 시작한 intent 를 가져오고 문자열을 추출합니다.
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // 레이아웃의 Textview 를 message 로 설정
        val textView = findViewById<TextView>(R.id.result_textview).apply {
            text=message
        }
    }
}