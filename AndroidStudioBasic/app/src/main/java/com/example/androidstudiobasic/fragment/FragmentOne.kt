package com.example.androidstudiobasic.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne:Fragment() {

//    //리스너 만들기(interface)
//    interface OnDataPassListener {
//        fun onDataPass(data:String?)
//    }
//
//    lateinit var dataPassListener : OnDataPassListener
//
//    override fun onAttach(context: Context) {
//        Log.d("life-cycle","F onAttach")
//        super.onAttach(context)
//        //형변환 방법
//        // - 1) "asdfg".toInt()
//        // - 2) "asdfg" as Int
//        dataPassListener = context as OnDataPassListener    //형변환
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?){
//        Log.d("life-cycle","F onCreate")
//        super.onCreate(savedInstanceState)
//    }
//
//    // fragment 가 인터페이스를 처음으로 그릴 때 호출됨
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Log.d("life-cycle","F onCreateView")
//        // fragment 가 인터페이스를 처음으로 그릴 때 호출됨.
//        // inflater -> 뷰를 그려주는 역할
//        // container -> 부모 뷰로부터 들러붙을곳
//        return inflater.inflate(R.layout.fragment_one,container,false)
//
//    }
//
//    // fragment 로 하고싶은 작업들을 여기에서 작성
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        Log.d("life-cycle","F onViewCreated")
//        super.onViewCreated(view, savedInstanceState)
//
//        // Activity 의 OnCreate 에서 했던 작업을 여기에서 한다.
//        pass.setOnClickListener {
//            dataPassListener.onDataPass("Good Bye") //리스너 장착
//        }
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        Log.d("life-cycle","F onActivityCreated")
//        val data = arguments?.getString("hello")
//        if (data != null) {
//            Log.d("data", data)
//        }
//        super.onActivityCreated(savedInstanceState)
//    }
//
//    override fun onStart() {
//        Log.d("life-cycle","F onStart")
//        super.onStart()
//    }
//
//    override fun onResume() {
//        Log.d("life-cycle","F onResume")
//        super.onResume()
//    }
//
//    override fun onPause() {
//        Log.d("life-cycle","F onPause")
//        super.onPause()
//    }
//
//    override fun onStop() {
//        Log.d("life-cycle","F onStop")
//        super.onStop()
//    }
//
//    override fun onDestroyView() {
//        Log.d("life-cycle","F onDestroyView")
//        super.onDestroyView()
//    }
//
//    override fun onDetach() {
//        Log.d("life-cycle","F onDetach")
//        super.onDetach()
//    }
}