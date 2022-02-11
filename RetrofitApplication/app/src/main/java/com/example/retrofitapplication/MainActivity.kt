package com.example.retrofitapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.class1.RetrofitClient
import com.example.retrofitapplication.class1.RetrofitInterface
import com.example.retrofitapplication.class1.data.BoxOfficeResult
import com.example.retrofitapplication.class1.recyclerview.MovieAdapter
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import retrofit2.Callback
import com.example.retrofitapplication.class1.data.Result


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: MovieAdapter

    private lateinit var retrofitClient: RetrofitClient
    private lateinit var retrofitInterface: RetrofitInterface

    private val API_KEY: String = "32959eb64a93e024aaf4bb540b00e85c"    // Api 키
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        // 초기화
        retrofitClient = RetrofitClient().getInstance()!!
        retrofitInterface = RetrofitClient().getRetrofitInterface()

        // 하루 전 날짜 구하기
        val day: Calendar = Calendar.getInstance()
        day.add(Calendar.DATE, -1)
        val time: String =SimpleDateFormat("yyyyMMdd").format(day.time).toString()

        retrofitInterface.getBoxOffice(API_KEY, time)!!.enqueue(object : Callback<Result?> {

            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                val result: Result? = response.body()
                val boxOfficeResult: BoxOfficeResult = result!!.boxOfficeResult
                Log.d("retrofit", "Data fetch success")
                mAdapter = MovieAdapter(boxOfficeResult.dailyBoxOfficeList!!)
                recyclerView.adapter = mAdapter
            }

            override fun onFailure(call: Call<Result?>?, t: Throwable) {
                Log.d("retrofit", t.message!!)
            }
        })

    }
}