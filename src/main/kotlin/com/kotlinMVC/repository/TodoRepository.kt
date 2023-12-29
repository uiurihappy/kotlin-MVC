package com.kotlinMVC.repository

import com.kotlinMVC.database.Todo

interface TodoRepository {
    fun save(todo: Todo): Todo
    fun saveAll(todoList: MutableList<Todo>): Boolean

    fun delete(index: Int): Boolean

    fun findOne(index: Int): Todo?

    fun findAll(): MutableList<Todo>

    fun update(index: Int, todo: Todo): Todo?

}