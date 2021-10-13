package com.example.androidpersonalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentFirst: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //frag1과 연결시켜 return 해줌
        return inflater.inflate(R.layout.activity_fragment_first,container,false)
    }
}