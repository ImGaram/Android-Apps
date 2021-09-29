package com.example.japtangg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.japtangg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val text = "데이터바인딩"
    val user = User("1312", "임거럼", "17")
    //전역 변수
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)   // binding 초기화

        binding.jap1 = this     // MainActivity 와 연결
    }

    fun change(view: View){
        // id 이름으로 바로 접근가능(apply)
        binding.apply {
            btn.setOnClickListener {
                text1.visibility = View.VISIBLE
                text1.text = "성공"
            }
        }
    }
}