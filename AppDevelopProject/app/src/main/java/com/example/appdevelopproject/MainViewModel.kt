package com.example.appdevelopproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appdevelopproject.recyclerview.Todo

class MainViewModel:ViewModel() {
    // 상태 변경 여부와 관찰이 가능한 liveData
    val todoLiveData = MutableLiveData<List<Todo>>()
    private val data = arrayListOf<Todo>()

    // 완료 여부
    fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone  // 객체의 주소값이기 때문에 값이 변함
        todoLiveData.value = data
    }

    // 데이터 추가
    fun addTodo(todo: Todo) {
        data.add(todo)
        todoLiveData.value = data   // value: 값 변경
    }

    // 데이터 삭제
    fun deleteTodo(todo: Todo) {
        data.remove(todo)   // remove = 삭제
        todoLiveData.value = data
    }
}