package com.example.rxjavaapplication.cheese_finder

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaapplication.R
import java.util.*

open class BaseSearchActivity: AppCompatActivity() {

    private lateinit var mCheeseSearchEngine: CheeseSearchEngine
    private lateinit var mQueryEditText: EditText
    private lateinit var mSearchButton: Button
    private lateinit var adapter: CheeseAdapter
    private lateinit var mProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CheeseAdapter()
        recyclerView.adapter = adapter

        mQueryEditText = findViewById(R.id.query_edit_text)
        mSearchButton = findViewById(R.id.search_button)
        mProgressBar = findViewById(R.id.progress_bar)

        val cheeses = resources.getStringArray(R.array.cheeses)
        mCheeseSearchEngine = CheeseSearchEngine(cheeses)
    }

    fun showProgressBar() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun showResult(result: MutableList<String>) {
        if (result.isEmpty()) {
            Toast.makeText(this, "nothing found", Toast.LENGTH_SHORT).show()
            adapter.setCheese(Collections.emptyList())
        } else {
            adapter.setCheese(result)
        }
    }
}