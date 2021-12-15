package com.example.androidkotlinbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidkotlinbook.databinding.ActivityActivity064ViewBindingBinding

class Activity_06_4_ViewBinding : AppCompatActivity() {
    // 뷰 바인딩 : xml 에 선언한 뷰 객체를 코드에서 쉽게 이용하는 방법
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 객체 이용법
        // 바인딩 객체 획득
        val binding = ActivityActivity064ViewBindingBinding.inflate(layoutInflater)
        // 액티비티 화면 출력
        setContentView(binding.root)

        // 뷰 객체 이용
        binding.visibleBtn.setOnClickListener {
            binding.targetView.visibility = View.VISIBLE
        }
        binding.invisibleBtn.setOnClickListener {
            binding.targetView.visibility = View.INVISIBLE
        }
    }
}