package com.example.appdevelopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdevelopproject.databinding.ActivityMainBinding
import com.example.appdevelopproject.recyclerview.Todo
import com.example.appdevelopproject.recyclerview.TodoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(viewModel.data, onClickDeleteIcon = {
                viewModel.deleteTodo(it)
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }, onClickItem = {
                viewModel.toggleTodo(it)
                binding.recyclerView.adapter?.notifyDataSetChanged()
            })
        }
        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editText.text.toString())
            viewModel.addTodo(todo)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
