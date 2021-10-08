package com.example.androidstudiobasic.Android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidstudiobasic.Fragment1
import com.example.androidstudiobasic.R

class Fragment2 : Fragment(){

    // fragment 가 인터페이스를 처음으로 그릴 때 호출됨
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment 가 인터페이스를 처음으로 그릴 때 호출됨.
        // inflater -> 뷰를 그려주는 역할
        // container -> 부모 뷰로부터 들러붙을곳
        return inflater.inflate(R.layout.fragment_two,container,false)

    }
}

private fun newInstant(): Fragment1 {
    val args =Bundle()
    val frag = Fragment1()
    frag.arguments = args
    return frag
}
