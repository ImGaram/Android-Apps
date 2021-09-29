package com.example.androidstudiobasic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent.*

class Intent1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        change_activity.setOnClickListener{
//            var intent = Intent(this@Intent1,Intent2::class.java)   //intent 생성

//            //key, value 방식 -> key 와 value 를 쌍으로 만들어 저장한다 -> Dictionary
//            intent.putExtra("number1",1)    // intent 에 값을 넣음
//            intent.putExtra("number2",2)
//            startActivity(intent)   // intent 로 이동

            // 읽는 사람 입장
//            val intent2 = Intent(this@Intent1, Intent2::class.java)
//            intent2.apply {
//                this.putExtra("number1",1)
//                this.putExtra("number2",1)
//            }
//            startActivityForResult(intent2,200)

            // 암시적 Intent
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 200){
            Log.d("number",""+requestCode)
            Log.d("number",""+resultCode)
            val result = data?.getIntExtra("result",0)
            Log.d("number",""+result)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}