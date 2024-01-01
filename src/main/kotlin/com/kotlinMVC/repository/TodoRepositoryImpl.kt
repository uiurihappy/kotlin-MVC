package com.kotlinMVC.repository

import com.kotlinMVC.database.Todo
import com.kotlinMVC.database.TodoDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl: TodoRepository{

    @Autowired
    lateinit var todoDatabase: TodoDatabase

    override fun save(todo: Todo): Todo? {

        // 1. index 검사
        // 2. index 값이 있으면 업데이트
        // 3. index 값이 없으면 생성
        return todo.index?.let {index ->
            // 값이 있을 때 update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        }?: kotlin.run {
            // 값이 없을 때 insert
            todo.apply {
                this.index = ++todoDatabase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDatabase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(index: Int): Boolean {
        return findOne(index)?.let {
            // 값이 있을 때
            todoDatabase.todoList.remove(it)
            true
        }?: kotlin.run {
            // 값이 없을 때
            false
        }
//        return todoDatabase.todoList.removeIf {
//            it.index == index
//        }
    }

    // 없을 수도 있기에 반환타입으로 null도 받을 수 있도록 ? 추가
    override fun findOne(index: Int): Todo? {
        // 동일한 인덱스의 데이터 찾으며 첫번째 데이터 반환
        return todoDatabase.todoList.first {
            it.index == index
        }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDatabase.todoList
    }
}