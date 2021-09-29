package com.example.recyclerviewpractice

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_recyclerview_item.view.*

//커스텀 뷰홀더
class MyViewHolder(itemView:View, recyclerViewInterface: MyRecyclerViewInterface): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var TAG:String="로그"

    private val usernameTextView = itemView.user_name_text
    private val profileImageView = itemView.profile_img

    private var myRecyclerViewInterface :MyRecyclerViewInterface?=null

    //기본 생성자
    init {
        Log.d(TAG,"MyViewHolder - init() called")

        itemView.setOnClickListener(this)
        this.myRecyclerViewInterface=recyclerViewInterface
    }

    //데이터와 뷰를 묶음
    fun bind(myModel: MyModel){
        Log.d(TAG,"MyViewHolder - bind() called")
        usernameTextView.text = myModel.name

        Glide
            .with(App.instance)
            .load(myModel.profileimg)
            //.centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(profileImageView)
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"MyViewHolder - onClick() called")
        this.myRecyclerViewInterface?.onItemClicked(adapterPosition)
    }
}