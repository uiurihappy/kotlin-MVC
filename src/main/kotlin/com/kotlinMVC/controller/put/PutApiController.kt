package com.kotlinMVC.controller.put

import com.kotlinMVC.model.http.Result
import com.kotlinMVC.model.http.UserRequest
import com.kotlinMVC.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping-put-method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {

        // user-response
        // apply-pattern
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "test@test.com"
                this.address = "seoul"
                this.phoneNumber = "010-2463-2396"
            })
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "test@test.com"
                this.address = "seoul"
                this.phoneNumber = "010-2463-2396"
            })
            this.userRequest = userList
        }
    }
}