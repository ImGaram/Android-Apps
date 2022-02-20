package com.example.retrofitapplication.lecture.recyclerview

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.SearchData
import com.example.retrofitapplication.lecture.utils.Constants.TAG

class SearchItemViewHolder(itemView: View, searchRecyclerViewInterface:ISearchHistoryRecyclerView)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mySearchRecyclerViewInterface: ISearchHistoryRecyclerView

    // 뷰 가져오기
    private val searchTermTextView = itemView.findViewById<TextView>(R.id.search_term_text)
    private val whenSearchedTextView = itemView.findViewById<TextView>(R.id.when_searched_text)
    private val deleteSearchButton = itemView.findViewById<ImageView>(R.id.delete_search_button)
    private val constraintSearchItem = itemView.findViewById<ConstraintLayout>(R.id.constraint_search_item)

    init {
        Log.d(TAG, "SearchItemViewHolder - init() called")
        // 리스너 연결
        deleteSearchButton.setOnClickListener(this)
        constraintSearchItem.setOnClickListener(this)
        this.mySearchRecyclerViewInterface = searchRecyclerViewInterface    // 뷰홀더에 init이 되면서 연결
    }

    // 데이터와 뷰를 묶는다
    fun bindWithView(searchItem: SearchData) {
        Log.d(TAG, "SearchItemViewHolder - bindWithView() called")

        whenSearchedTextView.text = searchItem.timeStamp
        searchTermTextView.text = searchItem.term
    }

    override fun onClick(p0: View?) {
        Log.d(TAG, "SearchItemViewHolder - onClick() called")
        when (p0) {
            deleteSearchButton -> {
                Log.d(TAG, "SearchItemViewHolder - 검색 삭제 버튼 클릭")
                // adapterPosition : 해당되는 뷰홀더에 인덱스값을 알려줌
                this.mySearchRecyclerViewInterface.onSearchItemDeleteClicked(adapterPosition)
            }
            constraintSearchItem -> {
                Log.d(TAG, "SearchItemViewHolder - 검색 아이템 클릭")
                this.mySearchRecyclerViewInterface.onSearchItemClicked(adapterPosition)
            }
        }
    }
}