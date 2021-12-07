package com.example.roomapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
    var view: View = v

    fun bind(item:Contacts) {
        view.mName.text = item.name
        view.mTel.text = item.tel
    }
}