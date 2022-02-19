package com.example.retrofitapplication.lecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.Photo
import com.example.retrofitapplication.lecture.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.google.android.material.appbar.MaterialToolbar

class PhotoCollectionActivity: AppCompatActivity() {
    // 데이터
    private var photoList = ArrayList<Photo>()
    // 어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d(TAG, "PhotoCollectionActivity - onCreate() called / searchTerm : $searchTerm, photoList.count() : ${photoList.count()}")

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        topAppBar.title = searchTerm

        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()
        this.photoGridRecyclerViewAdapter.submitList(photoList)

        val recyclerView = findViewById<RecyclerView>(R.id.my_photo_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        recyclerView.adapter = this.photoGridRecyclerViewAdapter
    } // onCreate

}