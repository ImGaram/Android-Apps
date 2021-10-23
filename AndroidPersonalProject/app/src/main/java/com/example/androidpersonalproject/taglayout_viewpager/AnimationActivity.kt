package com.example.androidpersonalproject.taglayout_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.androidpersonalproject.R
import com.google.android.material.tabs.TabLayout

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val pagerAdapter = TabPagerAdapter(supportFragmentManager)
        val pager=findViewById<ViewPager>(R.id.viewpager_ani)
        pager.adapter = pagerAdapter

        val tab =findViewById<TabLayout>(R.id.tab_layout)
        tab.setupWithViewPager(pager)
    }
}
