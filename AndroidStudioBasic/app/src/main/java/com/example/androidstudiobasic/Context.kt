package com.example.androidstudiobasic

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Context : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context)

        val context :Context = this //this 는 context 의 자식 클래스라 사용 가능함
        val applicationContext = getApplicationContext()

    }
}