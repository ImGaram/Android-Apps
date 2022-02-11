package com.example.retrofitapplication.class1.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.class1.data.DailyBoxOfficeList

class MovieAdapter(var items: List<DailyBoxOfficeList>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DailyBoxOfficeList = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rank = itemView.findViewById<TextView>(R.id.rank)
        val movieNm = itemView.findViewById<TextView>(R.id.movieNM)
        val openDt = itemView.findViewById<TextView>(R.id.openDt)

        fun setItem(item: DailyBoxOfficeList) {
            rank.text = item.rank
            movieNm.text = item.movieNm
            openDt.text = item.openDt
        }
    }
}