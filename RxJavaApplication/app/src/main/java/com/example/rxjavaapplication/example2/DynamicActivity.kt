package com.example.rxjavaapplication.example2

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.rxjavaapplication.R
import com.example.rxjavaapplication.example2.retrofit.RetrofitClient
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class DynamicActivity : BaseActivity() {

    lateinit var builder: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        val ReqButton = findViewById<Button>(R.id.button_req)
        val editUrl = findViewById<EditText>(R.id.edit_url)
        ReqButton.setOnClickListener {
            val baseUrl = editUrl.text.toString()
            RetrofitClient.setFullURL(baseUrl)

            disposable = RetrofitClient.Instance_Dynamic().getAnyInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.i("TEST: ", "success $result")
                        showMsg(result)
                    },
                    { error ->
                        Log.e("TEST: ", error.toString())
                        showMsg(error.toString())
                    }
                )

            editUrl.setText("https://")
        }
    }

    fun showMsg(msg: String) {
        builder = AlertDialog.Builder(this)

        builder.setTitle("Result")
        builder.setMessage(msg)
            .setPositiveButton("ok") { dialog, id ->
                dialog.cancel()
            }.show()
    }
}