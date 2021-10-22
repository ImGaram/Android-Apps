package com.example.androidpersonalproject.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidpersonalproject.R

class AniFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_ani_fragment2,container,false)
    }
}

private fun newInstance(): AniFragment1{
    val args = Bundle()
    val ani1 = AniFragment1()
    ani1.arguments = args
    return ani1
}
