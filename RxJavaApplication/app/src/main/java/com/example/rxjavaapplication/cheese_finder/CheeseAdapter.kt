package com.example.rxjavaapplication.cheese_finder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaapplication.R

class CheeseAdapter : RecyclerView.Adapter<CheeseAdapter.CheeseViewHolder>() {

    private var mCheese: MutableList<String>? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CheeseAdapter.CheeseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_1, parent, false)
        return CheeseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheeseAdapter.CheeseViewHolder, position: Int) {
        holder.title.text = mCheese!![position]
    }

    override fun getItemCount(): Int {
        return mCheese!!.size
    }

    fun setCheese(cheeses: MutableList<String>) {
        mCheese = cheeses
        notifyDataSetChanged()
    }

    inner class CheeseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(android.R.id.text1)
    }
}