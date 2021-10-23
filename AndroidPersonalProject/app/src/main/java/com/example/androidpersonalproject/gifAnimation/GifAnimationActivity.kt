package com.example.androidpersonalproject.gifAnimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.androidpersonalproject.R
import kotlinx.android.synthetic.main.activity_gif_animation.*

class GifAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_animation)

        Glide.with(this)
            .asGif()
            .load(R.raw.bonggocat1)
            .override(300,300)
            .into(gif_img1)

        load_gif_btn1.setOnClickListener {
            val intent = Intent(this, GifAnimation2::class.java)
            startActivity(intent)
        }
    }
}