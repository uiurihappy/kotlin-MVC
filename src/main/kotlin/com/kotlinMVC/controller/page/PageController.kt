package com.kotlinMVC.controller.page

import com.kotlinMVC.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    // localhost:8080/main
    @GetMapping("main")
    fun main(): String {
        println("main init")
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name = "ybchar"
            this.email = "uiurihappy@naver.com"
        }
    }
}