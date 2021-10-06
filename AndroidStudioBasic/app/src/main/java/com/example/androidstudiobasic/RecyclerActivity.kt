package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)


        val carList = ArrayList<CarForList>()
        for (i in 0 until 50){
            carList.add(CarForList(""+i+"번째 차",""+i+"순위 엔진"))
        }
        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this@RecyclerActivity))
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this@RecyclerActivity)
    }
}

class RecyclerViewAdapter(
    val itemList: ArrayList<CarForList>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val carName: TextView
        val carEngine: TextView

        // 초기화 init
        init {
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
        }
    }
    // 뷰를 만든다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent,false)
        return ViewHolder(view) // 30열의 itemView 로 향한다
    }
    // 뷰를 그린다
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.setText(itemList.get(position).name)
        holder.carEngine.setText(itemList.get(position).engine)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
