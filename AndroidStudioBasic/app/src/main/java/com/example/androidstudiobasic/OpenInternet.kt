package com.example.androidstudiobasic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_open_internet.*

class OpenInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_internet)

        address_edit_text

        open.setOnClickListener {
            val address = address_edit_text.text.toString()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
            startActivity(intent)
        }

//        address_edit_text.addTextChangedListener()    // 익명함수로 오브젝트 넣기기
        address_edit_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit","beforeTextChanged : " +s )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() == "abc"){
                    Log.d("edit","text is abc" )
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("edit","afterTextChanged : " +s )
            }

        })
    }
}