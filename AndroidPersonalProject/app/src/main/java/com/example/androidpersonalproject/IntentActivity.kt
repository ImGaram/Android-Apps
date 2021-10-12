package com.example.androidpersonalproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*

const val EXTRA_MESSAGE = "com.example.androidpersonalproject.MESSAGE"

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        // 클릭 리스너
        load_btn.setOnClickListener {
            // findViewById 로 xml 의 뷰들을 가져옴(EditText)
            val editText = findViewById<EditText>(R.id.text_intent)
            val message = editText.text.toString()
            // Intent 로 다음 activity 지정
            val intent = Intent(this, Intent2Activity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)    // putExtra => editText 값을 intent 에 추가
            }
            startActivity(intent)   // Intent2Activity
        }
    }
}