package com.example.androidstudiobasic.tabpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.androidstudiobasic.R
import com.google.android.material.tabs.TabLayout

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        val pagerAdapter = TabPagerAdapter(supportFragmentManager)

        val pager = findViewById<ViewPager>(R.id.view_pager)
        pager.adapter = pagerAdapter

        val tab = findViewById<TabLayout>(R.id.tab_layout)
        tab.setupWithViewPager(pager)
    }
}

