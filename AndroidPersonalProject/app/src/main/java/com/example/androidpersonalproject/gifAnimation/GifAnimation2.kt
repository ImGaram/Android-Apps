package com.example.androidpersonalproject.gifAnimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.androidpersonalproject.R
import kotlinx.android.synthetic.main.activity_gif_animation2.*

class GifAnimation2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_animation2)

        Glide.with(this)
            .asGif()
            .load(R.raw.bonggocat2)
            .override(560,560)
            .into(gif_img2)

        load_gif_btn2.setOnClickListener {
            val intent = Intent(this, GifAnimationActivity::class.java)
            startActivity(intent)
        }
    }
}