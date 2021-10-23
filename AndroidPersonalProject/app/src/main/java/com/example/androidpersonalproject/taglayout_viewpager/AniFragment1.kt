package com.example.androidpersonalproject.taglayout_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidpersonalproject.R

class AniFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_ani_fragment1,container,false)
    }
}

private fun newInstance(): AniFragment1{
    val args = Bundle()
    val ani1 = AniFragment1()
    ani1.arguments = args
    return ani1
}
