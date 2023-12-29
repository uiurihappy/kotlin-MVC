package com.kotlinMVC.controller.response

import com.kotlinMVC.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    // GET localhost:8080/api/response?age=10
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
        // java style
//        // 1. age가 null이면 400 에러
//        if (age == null)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age is null")
//
//        // 2. age > 20 ? 200 : 400 error
//        if (age < 20)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age is less than 20")

        // kotlin style
        return age?.let {
            // age is not null
            // it은 age를 지칭
            if (it < 20)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age is less than 20")
            println(age)
            return ResponseEntity.ok("OK")
        }?: kotlin.run {
            // age is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age is null")
        }


    }

    // 2. post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {

        return ResponseEntity.status(HttpStatus.OK).body(userRequest) // object Mapper를 통해서 object가 json으로 변환되어 response
    }

    // 3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 기존 데이터가 없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int?): ResponseEntity<Any> {
        println(id)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }

}