package com.example.androidstudiobasic

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_resource.*

class Resource : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        // resource 불러오기
        //1
        val ment = resources.getString(R.string.hello)
        Log.d("mentt","ment : "+ment)

        //2
        val ment2 = getString(R.string.hello)
        Log.d("mentt","ment : "+ment2)

        // color
        val color = getColor(R.color.textview_color)
        Log.d("mentt","color : " + color)

        bbutton.setBackgroundColor(getColor(R.color.textview_color))
    }
}