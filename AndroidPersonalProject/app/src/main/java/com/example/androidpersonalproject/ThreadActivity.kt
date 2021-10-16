package com.example.androidpersonalproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_thread.*
import java.text.SimpleDateFormat
import java.time.Clock
import java.util.*
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {
    var ClockTextView : TextView? = null
    private var Handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        // @SuppressLint : 개발자가 경고를 없애고 api 를 사용할수 있게 함
        @SuppressLint("HandlerLeak")
        Handler = object : Handler() {
            // handleMessage : 수신된 메시지 처리
            override fun handleMessage(msg: Message) {
                val cal = Calendar.getInstance()

                // SimpleDateFormat : 시간을 원하는 포맷으로 출력
                val tm = SimpleDateFormat("HH:mm:ss")
                val strTime = tm.format(cal.time)

                ClockTextView = findViewById(R.id.clock)
                ClockTextView?.setText(strTime)

                time_Btn.setOnClickListener {
                   Toast.makeText(this@ThreadActivity, "현재 시간은 $strTime 입니다",Toast.LENGTH_SHORT).show()
                }
            }
        }

        thread(start = true){
            while (true) {
                Thread.sleep(1000)
                Handler?.sendEmptyMessage(0)
            }
        }
    }
}