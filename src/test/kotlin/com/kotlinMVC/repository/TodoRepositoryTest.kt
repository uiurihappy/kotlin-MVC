package com.kotlinMVC.repository

import com.kotlinMVC.config.AppConfig
import com.kotlinMVC.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class) // Spring의 확장 기능을 사용
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun setUp() {
        todoRepositoryImpl.todoDatabase.init() // 초기화 함수 호출
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)
        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
    }

    @Test
    fun  saveAllTest() {
        val todoList = mutableListOf<Todo>().apply {
            for (i in 1..10) {
                add(Todo().apply {
                    this.title = "테스트 일정 $i"
                    this.description = "테스트"
                    this.schedule = LocalDateTime.now()
                })
            }
        }

        val result = todoRepositoryImpl.saveAll(todoList)
        Assertions.assertTrue(result)
    }

    @Test
    fun findOneTest() {

        val todoList = mutableListOf<Todo>().apply {
            for (i in 1..10) {
                add(Todo().apply {
                    this.title = "테스트 일정 $i"
                    this.description = "테스트 $i"
                    this.schedule = LocalDateTime.now()
                })
            }
        }

        todoRepositoryImpl.saveAll(todoList)
        val result = todoRepositoryImpl.findOne(2)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정 2", result?.title)
        Assertions.assertEquals("테스트 2", result?.description)
    }

    @Test
    fun updateTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "업데이트 일정"
            this.description = "업데이트 테스트"
            this.schedule = LocalDateTime.now()
        }
        val result = todoRepositoryImpl.save(newTodo)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(insertTodo?.index, result?.index)
        Assertions.assertEquals("업데이트 일정", result?.title)
        Assertions.assertEquals("업데이트 테스트", result?.description)
    }
}