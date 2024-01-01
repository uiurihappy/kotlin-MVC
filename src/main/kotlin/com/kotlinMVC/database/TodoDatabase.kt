package com.kotlinMVC.database

data class TodoDatabase (
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
) {
    fun init() {
        this.index = 0  // init 시 index 초기화
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")
    }
}
