package com.example.roomapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null
    var contactList = mutableListOf<Contacts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // db 초기화
        db = AppDatabase.getInstance(this)

        // 이전에 저장한 내용 모두 불러와서 추가
        val savedContacts = db!!.contactsDao().getAll()
        if (savedContacts.isNotEmpty())
            contactList.addAll(savedContacts)

        // 어답터
        val adapter = ContactsListAdapter(contactList)
        adapter.setItemClickListener(object : ContactsListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val contacts = contactList[position]
                db?.contactsDao()?.delete(contacts = contacts)   //db 에서 삭제
                contactList.removeAt(position)  // 리스트에서 삭제
                adapter.notifyDataSetChanged()  // 리스트뷰 갱신
            }
        })
        mRecyclerView.adapter = adapter

        val mPlusButton = findViewById<Button>(R.id.mPlusButton)
        mPlusButton.setOnClickListener {
            val random = Random()
            val num1 = random.nextInt(1000)
            val num2 = random.nextInt(10000)
            val num3 = random.nextInt(10000)
            val rndNumber = String.format("%03d-%04d-%04d", num1, num2, num3)

            val contact = Contacts(0,"New$num1", rndNumber)     // Contact 생성
            db?.contactsDao()?.insertAll(contact)    // db 에 추가
            contactList.add(contact)    // 리스트뷰 추가
            adapter.notifyDataSetChanged()  // 리스트뷰 갱신
        }
    }
}