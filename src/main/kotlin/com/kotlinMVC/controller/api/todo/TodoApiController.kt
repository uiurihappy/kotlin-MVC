package com.kotlinMVC.controller.api.todo

import com.kotlinMVC.model.http.TodoDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    // GET
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?) {
    }

    // POST
    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto) {

    }

    // PUT
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto: TodoDto) {

    }

    // DELETE
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int) {

    }
}