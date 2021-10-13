package com.example.androidpersonalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        // show_frag1을 눌렀을 때 beginTransaction()을 이용해 Fragment 를 만들어주고, FragmentFirst 으로 대체해준다.
        show_frag1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FragmentFirst())
                .commit()
        }

        show_frag2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FragmentSecond())
                .commit()
        }

        show_frag3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FragmentThird())
                .commit()
        }
    }
}