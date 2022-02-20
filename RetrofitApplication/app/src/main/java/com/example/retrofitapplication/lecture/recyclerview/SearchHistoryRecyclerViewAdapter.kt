package com.example.retrofitapplication.lecture.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.SearchData
import com.example.retrofitapplication.lecture.utils.Constants.TAG

class SearchHistoryRecyclerViewAdapter(ISearchHistoryRecyclerView: ISearchHistoryRecyclerView)
    : RecyclerView.Adapter<SearchItemViewHolder>() {

    private var searchHistoryList = ArrayList<SearchData>()
    private var iSearchHistoryRecyclerView: ISearchHistoryRecyclerView? = null

    init {
        Log.d(TAG, "SearchHistoryRecyclerViewAdapter - init() called")
        this.iSearchHistoryRecyclerView = ISearchHistoryRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_item, parent, false)
        return SearchItemViewHolder(view, this.iSearchHistoryRecyclerView!!)    // 3. 뷰홀더에 인터페이스 연결
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bindWithView(this.searchHistoryList[position])
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    fun submitList(searchHistoryList: ArrayList<SearchData>) {
        this.searchHistoryList = searchHistoryList
    }
}