package com.example.retrofitapplication.lecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.databinding.ActivityPhotoCollectionBinding
import com.example.retrofitapplication.lecture.model.Photo
import com.example.retrofitapplication.lecture.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.google.android.material.appbar.MaterialToolbar

class PhotoCollectionActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPhotoCollectionBinding
    private var photoList = ArrayList<Photo>()
    private lateinit var adapter:PhotoGridRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "PhotoCollectionActivity - onCreate() called")

        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")

        recyclerView = findViewById(R.id.my_photo_recycler_view)

        this.adapter = PhotoGridRecyclerViewAdapter()
        this.adapter.submitList(photoList)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = this.adapter

        val topAppBarTitle = findViewById<MaterialToolbar>(R.id.top_app_bar)
        topAppBarTitle.title = searchTerm

        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d(TAG, "onCreate / searchTerm : $searchTerm, photoList.count() : ${photoList.count()}")
    }
}