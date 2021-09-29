package com.example.androidstudiobasic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var new = "0"
        var old = "0"

        one.setOnClickListener {
            new +=1
            res.setText(new)
        }
        two.setOnClickListener {
            new +=2
            res.setText(new)
        }
        three.setOnClickListener {
            new +=3
            res.setText(new)
        }
        four.setOnClickListener {
            new +=4
            res.setText(new)
        }
        five.setOnClickListener {
            new +=5
            res.setText(new)
        }
        six.setOnClickListener {
            new +=6
            res.setText(new)
        }
        seven.setOnClickListener {
            new +=7
            res.setText(new)
        }
        eight.setOnClickListener {
            new +=8
            res.setText(new)
        }
        nine.setOnClickListener {
            new +=9
            res.setText(new)
        }
        zero.setOnClickListener {
            new +=0
            res.setText(new)
        }

        plus.setOnClickListener {
            old = (old.toInt() + new.toInt()).toString()    //형변환
            new="0"
            res.setText(old)
        }

        clear.setOnClickListener {
            new="0"
            old="0"
            res.setText("0")
        }
    }
}