package com.kotlinMVC.controller.post

import com.kotlinMVC.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String{
        return "post-mapping"
    }

    // 기존 get-mapping에서 동일한 URL 주소여도 Method가 다르기에 충돌이 없다.
    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // body에 내용을 넣어 요청 - objectMapper 사용 (json -> object, object -> json)
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {

        // json -> object
        println(userRequest)

        // object -> json
        return userRequest
    }
}