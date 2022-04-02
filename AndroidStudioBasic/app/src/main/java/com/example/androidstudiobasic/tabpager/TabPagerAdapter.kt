package com.example.androidstudiobasic.tabpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidstudiobasic.fragment.Fragment1
import com.example.androidstudiobasic.fragment.Fragment2
import com.example.androidstudiobasic.fragment.Fragment3


class TabPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){
    // getCount(int) : 총 몇 개의 화면을 구성할 지 지정
    override fun getCount(): Int = 3

    // getItem(int) : 순서(position)에 따른 화면 지정
    override fun getItem(position: Int): Fragment {
        val fragment = when(position){
            0-> Fragment1()
            1-> Fragment2()
            2-> Fragment3()
            else -> Fragment1()
        }
        return fragment
    }

    // getPageTitle(int) : Tab의 제목 지정
    override fun getPageTitle(position: Int): CharSequence? {
        var title = when(position){
            0 -> "one"
            1 -> "two"
            2 -> "three"
            else -> "main"
        }
        return title
    }

}