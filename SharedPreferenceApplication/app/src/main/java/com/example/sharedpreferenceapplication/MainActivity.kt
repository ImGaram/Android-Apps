package com.example.sharedpreferenceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferenceapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO:: 저장된 데이터 로드
        loadData()
    }

    // activity가 종료되는 시점에 호출
    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    // 저장된 edit text 불러오기
    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        binding.etHello.setText(pref.getString("name", "")) // 1. 키값, 2. 1의 값이 존재하지 않을 경우 대체
    }
    // edit text값 저장
    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()  // pref 수정(추가)
        edit.putString("name", binding.etHello.text.toString())  // key, 실제 담을 값
        edit.apply()    // 값을 저장함
    }
}