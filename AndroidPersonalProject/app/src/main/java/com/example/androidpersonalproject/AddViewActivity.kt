package com.example.androidpersonalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.LinearLayout
import android.widget.TextView
import java.util.zip.Inflater

class AddViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        val Imgaram = ArrayList<meForList>()
        for (i in 0 until 10) {
            Imgaram.add(meForList("임가람 $i"))
        }

        val container = findViewById<LinearLayout>(R.id.layout_addview)
        val inflater = LayoutInflater.from(this@AddViewActivity)
        for (i in 0 until Imgaram.size) {
            val itemView =inflater.inflate(R.layout.add_view_item,null)
            val myNameView = itemView.findViewById<TextView>(R.id.addview_name)

            myNameView.setText(Imgaram.get(i).name)
            container.addView(itemView)
        }
    }
}

class meForList(val name:String){

}
