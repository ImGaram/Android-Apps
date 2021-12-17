package com.example.androidkotlinbook.userEvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.androidkotlinbook.R
import com.example.androidkotlinbook.databinding.Activity085TouchKeyEventBinding
import org.w3c.dom.Text

class Activity_08_5_TouchKeyEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity085_touch_key_event)
    }

    // 터치 이벤트
    // 화면을 터치하면 자동으로 호출되는 메서드
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 터치 이벤트가 발생한 좌표 얻기
                Log.d("im","${event.x}, ${event.y}")
                Toast.makeText(this, "touch down", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_UP -> {
                Toast.makeText(this, "touch up", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onTouchEvent(event)
    }

    // 키 이벤트 : 사용자가 폰의 키를 누르는 순간에 발생함
    // 정의해야 할 콜백 함수 : onKeyDown(키를 누른 순간), onKeyUp(키를 떼는 순간), onKeyLongPress(키를 오래 누르는 순간)
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("im","on key up")
        return super.onKeyUp(keyCode, event)
    }
    // 어떤 키를 눌렀는지 식별
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val text = findViewById<TextView>(R.id.changeText)
        when (keyCode) {
            KeyEvent.KEYCODE_0 -> {
                text.text = "0 키를 눌렀군요"
            }
            KeyEvent.KEYCODE_A -> {
                text.text = "A 키를 눌렀군요"
            }
            // 뒤로가기 버튼과 볼륨 조절 버튼의 이벤트 처리
            KeyEvent.KEYCODE_BACK -> text.text = "뒤로가기 키를 눌렀군여"
            KeyEvent.KEYCODE_VOLUME_UP -> text.text = "volume up 키를 눌렀군여"
            KeyEvent.KEYCODE_VOLUME_DOWN -> text.text = "volume down 키를 눌렀군여"
        }
        return super.onKeyDown(keyCode, event)
    }

    // 뒤로가기 버튼의 이벤트 처리(2)
    override fun onBackPressed() {
        val text = findViewById<TextView>(R.id.changeText)
        text.text = "뒤로가기 키를 눌렀군여"
        super.onBackPressed()
    }
}