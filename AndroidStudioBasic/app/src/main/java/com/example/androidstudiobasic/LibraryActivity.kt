package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library_acticity.*

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_acticity)

        Glide
            .with(this@LibraryActivity)
            .load("https://t1.daumcdn.net/cfile/tistory/2550AC3855A1FEBB26")
            .centerCrop()
            .into(glide)

        Glide
            .with(this@LibraryActivity)
            .load("https://t1.daumcdn.net/cfile/tistory/2550AC3855A1FEBB26")
            .into(glide2)
    }
}