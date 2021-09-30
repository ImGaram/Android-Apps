package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class AddviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addview)

        // itemList 준비
        val carList = ArrayList<CarForList>()
        for (i in 0 until 10){
            carList.add(CarForList(""+i+"번째 차",""+i+"순위 엔진"))
        }

        // container 가져오기
        val container = findViewById<LinearLayout>(R.id.addview_container)
        // layoutInflater 가져오기
        val inflater = LayoutInflater.from(this@AddviewActivity)
        for (i in 0 until carList.size){
            // inflate 하기
            val itemView = inflater.inflate(R.layout.item_view, null)
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)

            carNameView.setText(carList.get(i).name)
            carEngineView.setText(carList.get(i).engine)
            container.addView(itemView)
        }
    }

}

class CarForList(val name: String, val engine:String){

}
