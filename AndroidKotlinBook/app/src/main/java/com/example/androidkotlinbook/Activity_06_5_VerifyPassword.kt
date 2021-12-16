package com.example.androidkotlinbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidkotlinbook.databinding.Activity065VerifyPasswordBinding

class Activity_06_5_VerifyPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity065VerifyPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val e = binding.editPassword
        val data = e.text
        binding.savePassword.setOnClickListener {
            Toast.makeText(this,"비밀번호는 $data", Toast.LENGTH_SHORT).show()
        }
        binding.forgetPassword.setOnClickListener {
            Toast.makeText(this,"현재 비밀번호는 $data", Toast.LENGTH_SHORT).show()
        }
    }
}