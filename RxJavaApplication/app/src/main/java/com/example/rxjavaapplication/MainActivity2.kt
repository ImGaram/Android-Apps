package com.example.rxjavaapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.rxjavaapplication.example2.BaseActivity
import com.example.rxjavaapplication.example2.DynamicActivity
import com.example.rxjavaapplication.example2.Modules
import com.example.rxjavaapplication.example2.retrofit.RetrofitClient
import com.example.rxjavaapplication.example2.retrofit.WikiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.lang.NullPointerException

class MainActivity2 : BaseActivity() {

    val TAG = "WikiResultLog"
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
        val buttonDynamic = findViewById<Button>(R.id.button_dynamic)

        buttonSearch.setOnClickListener {
            val searchKey = editTextKeyword.text.toString()
            beginSearch(searchKey)
        }

        buttonDynamic.setOnClickListener {
            val intent = Intent(this, DynamicActivity::class.java)
            startActivity(intent)
        }
    }

    private fun beginSearch(srsearch: String) {
        disposable = RetrofitClient.Instance_Wiki.hitCountWithResponseCode("query", "json", "search", srsearch)
            .subscribeOn(Schedulers.io())
            .map { t->
                if (t.isSuccessful) {
                    t
                } else {
                    throw HttpException(t)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.isSuccessful) {
                        val res: Modules.Result = result.body()
                            ?: Modules.Result(Modules.Query(Modules.SearchInfo(-1)))
                        result.body()?.let {
                            try {
                                showResult(res.query.searchInfo.totalhits)
                            } catch (e: NullPointerException) {
                                Log.e(TAG, e.toString())
                            }
                        }
                        Log.i("TEST: ", "success " + result.code())
                    } else {
                        Log.i("TEST: ", "failed " + result.code())
                    }
                },
                { error ->
                    if (error is HttpException) {
                        showError(error.message.toString())
                        Log.i(TAG, "TEST Error: ${error.code()} exception.response.code : ${error.response()?.code()}")
                    }
                }
            )
    }

    private fun showResult(totalhits: Int) {
        Log.d(TAG, "Result hits: $totalhits")
        textViewResult?.text = totalhits.toString()
    }

    private fun showError(message: String) {
        Log.d(TAG, "Error Msg: $message")
    }
}