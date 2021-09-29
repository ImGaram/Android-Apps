package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listener.*

class Listener : AppCompatActivity() {

    var number = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)

        //뷰를 액티비티로 가져오는 방법
        //1)직접 찾기
        //val textView: TextView = findViewById(R.id.hello)
        //2)xml import 하기
        //hello

        //익명함수(동일한 역할)
        //1 -> 람다 방식
        hello.setOnClickListener {
            Log.d("click","Click!")
        }

        //2 -> 익명 함수 방식
        hello.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("click","Click!")
            }
        })

        //3 -> 이름이 필요한 경우(click)
        val click = object :View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("click","Click!")
                hello.setText("안녕하세요")
                image.setImageResource(R.drawable.ic_launcher_background)
                image.setImageResource(R.drawable.img)
                number+=10
                Log.d("number",""+number)   //다른 자료형 string으로 변경법
            }
        }

        hello.setOnClickListener(click)

        //뷰를 조작하는 함수들
        //1) -> setText
        //2) -> setImageResource
    }
}