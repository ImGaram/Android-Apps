package com.example.androidkotlinbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// 기본적인 뷰
// 텍스트 뷰
// 속성 => text : 출력할 문자열 지정
// textColor : 문자열의 색상 지정
//  testSize : 문자열의 크기 지정
// textStyle : 문자열의 스타일 지정
class Activity_06_3_BasicView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity063_basic_view)
    }
}