package com.kotlinMVC.database

data class TodoDatabase (
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
) {
    fun init() {
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")
    }
}
