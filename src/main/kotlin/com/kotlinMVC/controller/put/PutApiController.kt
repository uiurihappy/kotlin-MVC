package com.kotlinMVC.controller.put

import com.kotlinMVC.model.http.Result
import com.kotlinMVC.model.http.UserRequest
import com.kotlinMVC.model.http.UserResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*

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
    fun putMappingObject(
        @Valid  // validation catch
        @RequestBody userRequest: UserRequest,
        bindingResult: BindingResult
    ): Any {

        if (bindingResult.hasErrors()) {
            // 500 Error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val message = it.defaultMessage
                // field내에 field 값을 불러옴
                msg.append("${field.field}: $message \n")
            }
            return ResponseEntity.internalServerError().body(msg.toString())
        }

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
                this.phoneNumber = "010-3735-2396"
            })
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "test@test.com"
                this.address = "seoul"
                this.phoneNumber = "010-3735-2396"
            })
            this.user = userList
        }
    }
}