package com.kotlinMVC.controller.get

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController     // REST API Controller
@RequestMapping("/api") // localhost:8080/api
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello kotlin";
    }
}