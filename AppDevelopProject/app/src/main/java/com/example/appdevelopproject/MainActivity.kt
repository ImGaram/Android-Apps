package com.example.appdevelopproject

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdevelopproject.databinding.ActivityMainBinding
import com.example.appdevelopproject.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data.add(Todo("숙제"))
        data.add(Todo("청소", true))

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(data, onClickDeleteIcon = {
                deleteTodo(it)
            }, onClickItem = {
                toggleTodo(it)
            })
        }
        binding.addButton.setOnClickListener {
            addTodo()
        }
    }

    // 완료 여부
    private fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone  // 객체의 주소값이기 때문에 값이 변함
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    // 데이터 추가
    private fun addTodo() {
        val todo = Todo(binding.editText.text.toString())
        data.add(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    // 데이터 삭제
    private fun deleteTodo(todo: Todo) {
        data.remove(todo)   // remove = 삭제
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }


}

// 기본값으로 false 를 넣어서 add 할때 생략이 가능하다
data class Todo(
    val text: String,
    var isDone: Boolean = false,
)  // data class 는 자동으로 getter setter 가 구현된다.

class TodoAdapter(
    private val myDataset: List<Todo>, val onClickDeleteIcon: (todo: Todo) -> Unit,
    val onClickItem: (todo: Todo) -> Unit,
) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = myDataset[position]
        holder.binding.todoText.text = myDataset[position].text

        if (todo.isDone) {
            // apply = 중복되는 값 줄일 떄 사용
            holder.binding.todoText.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.binding.todoText.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        holder.binding.deleteImageView.setOnClickListener {
            onClickDeleteIcon.invoke(todo)  // invoke = 함수 실행
        }

        holder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

}
