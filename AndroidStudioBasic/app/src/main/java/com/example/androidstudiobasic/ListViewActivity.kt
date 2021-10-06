package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 50){
            carList.add(CarForList(""+i+"번째 차",""+i+"순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, LayoutInflater.from(this@ListViewActivity))
        listview.adapter = adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this@ListViewActivity, carName + " " + carEngine, Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(
    val carForList: ArrayList<CarForList>,
    val layoutInflater: LayoutInflater): BaseAdapter() {

    // 전체의 사이즈
    override fun getCount(): Int {
        // 그리고자 하는 아이템 리스트의 전체 개수
        return carForList.size
    }
    // 아이템 하나를 가져옴
    override fun getItem(position: Int): Any {
        // 그리고자 하는 아이템 리스트의 하나(포지션에 해당하는)
        return carForList.get(position)
    }
    // 뷰를 그리는 용도
    override fun getItemId(position: Int): Long {
        // 해당 포지션에 위치해 있는 아이템뷰에 아이디 설정
        return position.toLong()
    }
    // 리스트의 아이디 부여
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null){
            Log.d("convert", "1")
            view = layoutInflater.inflate(R.layout.item_view,null)
            holder = ViewHolder()
            // findViewById 로 찾은 다음 holder 에 집어넣음
            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)
            // holder 를 찾게 함
            view.tag = holder
        }
        else {
            Log.d("convert", "2")
            holder = convertView.tag as ViewHolder
            view = convertView
        }
        holder.carName?.setText(carForList.get(position).name)
        holder.carEngine?.setText(carForList.get(position).engine)

        return view
    }

}

class ViewHolder{
    var carName: TextView? = null
    var carEngine: TextView? = null
}
