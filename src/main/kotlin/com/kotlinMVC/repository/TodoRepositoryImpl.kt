package com.kotlinMVC.repository

import com.kotlinMVC.database.Todo
import com.kotlinMVC.database.TodoDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoRepositoryImpl: TodoRepository{

    @Autowired
    lateinit var todoDatabase: TodoDatabase

    override fun save(todo: Todo): Todo {
        return todo.apply {
            this.index = todoDatabase.index++
        }.run {
            todoDatabase.todoList.add(todo)
            this
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(index: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findOne(index: Int): Todo? {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Todo> {
        TODO("Not yet implemented")
    }

    override fun update(index: Int, todo: Todo): Todo? {
        TODO("Not yet implemented")
    }

}