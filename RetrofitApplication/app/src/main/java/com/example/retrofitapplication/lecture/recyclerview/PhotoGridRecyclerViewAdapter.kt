package com.example.retrofitapplication.lecture.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.Photo

class PhotoGridRecyclerViewAdapter : RecyclerView.Adapter<PhotoItemViewHolder>() {

    private var photoList = ArrayList<Photo>()

    // 뷰홀더와 레이아웃 연결
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PhotoItemViewHolder {
        return PhotoItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_photo_item, parent, false))
    }

    // 뷰가 묶였을떄 데이터를 뷰홀더에 넘긴다
    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bindItemView(this.photoList[position])
    }

    // 보여줄 목록의 수
    override fun getItemCount(): Int {
        return photoList.size
    }

    // 외부에서 어답터에 데이터를 넣어준다
    fun submitList(photoList: ArrayList<Photo>) {
        this.photoList = photoList
    }
}