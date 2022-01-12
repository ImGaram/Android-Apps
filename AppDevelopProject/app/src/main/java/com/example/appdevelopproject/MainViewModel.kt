package com.example.appdevelopproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appdevelopproject.recyclerview.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel:ViewModel() {
    // 상태 변경 여부와 관찰이 가능한 liveData
    val todoLiveData = MutableLiveData<List<Todo>>()
    private val data = arrayListOf<Todo>()
    val db = Firebase.firestore     // db 객체 생성

    init {
        fetchData()
    }

    fun fetchData() {
        // 데이터 읽어오기
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            db.collection(user.uid)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }
                    data.clear()
                    for (document in value!!) {
                        // 데이터 읽기
                        val todo = Todo(
                            document.getString("text") ?: "",
                            document.getBoolean("isDone") ?: false  // 널이면 false
                        )
                        data.add(todo)
                    }
                    todoLiveData.value = data
                }
//                .get()
//                .addOnSuccessListener { result ->
//                    data.clear()
//                    for (document in result) {
//                        // 데이터 읽기
//                        val todo = Todo(
//                            document.data.get("text").toString(),
//                            document.data.get("isDone") as Boolean
//                        )
//                        data.add(todo)
//                    }
//                    // live data 적용
//                    todoLiveData.value = data
//                }
        }

    }

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