package com.example.recyclerviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyRecyclerViewInterface {

    var TAG:String="로그"

    //데이터를 담을 그릇
    var modelList=ArrayList<MyModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    //뷰가 화면에 드려질때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"MainActivity-onCreate() called")
        Log.d(TAG,"MainActivity-반복문 돌리기 전 this.modelList.size:${this.modelList.size}")

        //반복문
        for (i in 1..10){
            val myModel=MyModel(name = "임가람 $i", profileimg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3S8keZgjOaeVXWd4LLWJlHHhX1Khf25x8_g&usqp=CAU")
            this.modelList.add(myModel)
        }
        Log.d(TAG,"MainActivity - 반복문 돌린 후 this.modelList.size : ${this.modelList.size}")

        //어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)
        myRecyclerAdapter.submitList(this.modelList)

        my_recycler_view.apply {
            // 리사이클러뷰 설정
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL, false)
            // 어답터 장착
            adapter=myRecyclerAdapter
        }

    }

    override fun onItemClicked(position:Int) {
        Log.d(TAG,"MainActivity - onItemClick() called")

        //var name: String? = null

        // 값이 비어있으면 "" 를 넣음
        // unwrapping - 언랩핑

        val title: String = this.modelList[position].name?:""

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("$title 과 함께하는 빡코딩! :)")
            .setPositiveButton("오케이"){ dialog, id ->
                Log.d(TAG,"MainActivity - 다이얼로그 확인 버튼 클릭됨")
            }
            .show()
    }
}