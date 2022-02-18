package com.example.retrofitapplication.lecture.recyclerview

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.App
import com.example.retrofitapplication.lecture.model.Photo
import com.example.retrofitapplication.lecture.utils.Constants.TAG

class PhotoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    // 뷰를 가져온다
    private val photoImageView = itemView.findViewById<ImageView>(R.id.photo_image)
    private val photoCreatedAtText = itemView.findViewById<TextView>(R.id.created_at_text)
    private val photoLikesCount = itemView.findViewById<TextView>(R.id.likes_count_text)

    // 데이터와 뷰를 묶는다
    fun bindItemView(photoItem:Photo) {
        Log.d(TAG, "PhotoItemViewHolder - bindItemView() called ")

        photoCreatedAtText.text = photoItem.createdAt
        photoLikesCount.text = photoItem.likesCount.toString()

        // 이미지 설정
        Glide.with(App.instance)
            .load(photoItem.thumbnail)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .into(photoImageView)
    }

}