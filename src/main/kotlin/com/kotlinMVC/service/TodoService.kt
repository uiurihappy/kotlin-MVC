package com.kotlinMVC.service

import com.kotlinMVC.database.Todo
import com.kotlinMVC.database.convertTodo
import com.kotlinMVC.model.http.TodoDto
import com.kotlinMVC.model.http.convertTodoDto
import com.kotlinMVC.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

/**
 * 현업에서는 아래처럼 안사용하고
 * model mapper와 kotlin reflection을 많이 사용한다고 한다.
 */
@Service
class TodoService(
    // DI
    val todoRepositoryImpl: TodoRepositoryImpl
) {
    // C
    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // R
    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    // U
    // TODO: index 검증 및 index 해당되는 데이터 갱신
    fun update(todoDto: TodoDto, _index: Int): TodoDto? {
//        // _index 유효성 검사
//        if (_index < 0 || _index >= todoRepositoryImpl.size) {
//            // 유효하지 않은 인덱스인 경우 예외 처리 또는 null 반환 등을 수행
//            return null
//        }

        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}