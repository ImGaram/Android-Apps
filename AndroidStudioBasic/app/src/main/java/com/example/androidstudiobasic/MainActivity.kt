package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d("life-cycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life-cycle","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life-cycle","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life-cycle","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life-cycle","onDestroy")
    }
}