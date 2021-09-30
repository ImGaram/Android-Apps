package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity(), FragmentOne.OnDataPassListener {

    override fun onDataPass(data: String?) {
        Log.d("pass", ""+data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("life-cycle","onCreate")

        val fragmentOne: FragmentOne = FragmentOne()    // 버튼1,2 둘 다 쓰기 위함
        // Fragment 에 데이터를 넣기
        val bundle: Bundle = Bundle()
        bundle.putString("hello","hello")
        fragmentOne.arguments=bundle

        button.setOnClickListener {
            // Fragment 를 동적으로 작동하는 방법
            // Fragment 붙이는 방법 replace/add
            val fragmentManager: FragmentManager = supportFragmentManager

            // Transaction
            // 작업의 단위 -> 시작과 꿑이 있음
            var fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentOne)
            fragmentTransaction.commit()
            // 끝을 내는 방법
            // commit   -> 시간 될 때 함 (안정적)
            //commitNow -> 지금 당장 함
        }

        button2.setOnClickListener {
            // Fragment remove/detach 하는 방법
            val fragmentManager = supportFragmentManager
            val fragmentTransaction= fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragmentOne)
            fragmentTransaction.commit()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("life-cycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life-cycle","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life-cycle","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life-cycle","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life-cycle","onDestroy")
    }
}