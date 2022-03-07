package com.example.rxjavaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.rxjavaapplication.example2.Modules
import com.example.rxjavaapplication.example2.retrofit.WikiApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity2 : AppCompatActivity() {

    val TAG = "WikiResultLog"
    var disposable: Disposable? = null
    private val wikiApiService by lazy {
        WikiApiService.create()
    }
    var textViewResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textViewResult = findViewById(R.id.textViewRes)
        val editTextKeyword = findViewById<EditText>(R.id.editTextKeyword)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        buttonSearch.setOnClickListener {
            val searchKey = editTextKeyword.text.toString()
            beginSearch(searchKey)
        }
    }

    private fun beginSearch(srsearch: String) {
        disposable = wikiApiService.hitCountWithResponseCode("query", "json", "search", srsearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ result ->
                if (result.isSuccessful) {
                    val res: Modules.Result =
                        result.body() ?: Modules.Result(Modules.Query(Modules.SearchInfo(-1)))
                    result.body()?.let {
                        showResult(res.query.searchInfo.totalhits)
                    }
                    Log.i(TAG, "success: ${result.code()}")
                } else {
                    Log.i(TAG, "failed: ${result.code()}")
                }
            }, {
                    error -> showError(error.message.toString())
            })
    }

    private fun showResult(totalhits: Int) {
        Log.d(TAG, "Result hits: $totalhits")
        textViewResult?.text = totalhits.toString()
    }

    private fun showError(message: String) {
        Log.d(TAG, "Error Msg: $message")
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}