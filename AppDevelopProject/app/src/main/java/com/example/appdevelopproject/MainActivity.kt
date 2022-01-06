package com.example.appdevelopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdevelopproject.databinding.ActivityMainBinding
import com.example.appdevelopproject.recyclerview.Todo
import com.example.appdevelopproject.recyclerview.TodoAdapter
import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {
    val RC_SIGN_IN = 1000

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 이메일 로그인 구현
        val provider = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        // 첫 화면에 로그인 화면 추가
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .build(), RC_SIGN_IN
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(emptyList(), onClickDeleteIcon = {
                viewModel.deleteTodo(it)
            }, onClickItem = {
                viewModel.toggleTodo(it)
            })
        }
        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editText.text.toString())
            viewModel.addTodo(todo)
        }

        // 관찰하는 코드
        viewModel.todoLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as TodoAdapter).setData(it)
        })
    }
}
