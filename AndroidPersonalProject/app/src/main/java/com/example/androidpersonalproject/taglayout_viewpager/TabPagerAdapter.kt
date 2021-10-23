package com.example.androidpersonalproject.taglayout_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int  = 2

    override fun getItem(position: Int): Fragment {
        val fragment = when(position) {
            0->AniFragment1()
            1->AniFragment2()
            else -> AniFragment1()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = when(position){
            0->"cat1"
            1->"cat2"
            else -> "main"
        }
        return title
    }
}