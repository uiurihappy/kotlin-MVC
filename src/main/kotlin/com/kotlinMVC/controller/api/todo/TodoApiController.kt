package com.kotlinMVC.controller.api.todo

import com.kotlinMVC.model.http.TodoDto
import com.kotlinMVC.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {

    // GET
    // CREATE 이기에 statusCode는 201
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any?> {

        return index?.let {
            todoService.read(it)
        }?.let {
            ResponseEntity.status(HttpStatus.CREATED).body(it)
        } ?: kotlin.run {
//            readAll()   // 이렇게 하였을 시 반환 타입이 맞지않아 302 redirect를 한다고도 한다.
            ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all")
                .build()
        }
    }

    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    // POST
    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    // PUT
    // UPDATE이기에 statusCode는 200
    @PutMapping(path = ["/{index}"])
    fun update(@Valid @RequestBody todoDto: TodoDto, @PathVariable _index: Int): ResponseEntity<TodoDto> {
        return ResponseEntity.ok(todoService.update(todoDto, _index))
    }

    // DELETE
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Void> {
        if (!todoService.delete(_index)) {
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }
}