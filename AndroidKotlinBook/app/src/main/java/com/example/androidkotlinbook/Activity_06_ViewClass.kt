package com.example.androidkotlinbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

// 뷰 클래스
// 뷰 객체의 기본 구조 : 화면 구성과 관련된 클래스를 통칭하는 말
class Activity_06_ViewClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_062)   // xml 화면 출력

        // 코드에서 xml 에 입력한 객체 사용법
        val textView1: TextView = findViewById(R.id.text1)  // id 값으로 뷰 객체 획득함
        // 제너릭으로 가져오는 방법
        val textView2 = findViewById<TextView>(R.id.text1)

        // 코드에서 visible 속성 변경
        val btn = findViewById<Button>(R.id.a)
        val btn2 = findViewById<Button>(R.id.b)
        btn.setOnClickListener {
            btn.visibility = View.INVISIBLE       // 보이기
            btn2.visibility = View.VISIBLE       // 제거하기
        }
        btn2.setOnClickListener {
            btn2.visibility = View.INVISIBLE
            btn.visibility = View.VISIBLE
        }
    }
}