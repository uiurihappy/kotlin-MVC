package com.kotlinMVC.controller.get

import com.kotlinMVC.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController     // REST API Controller
@RequestMapping("/api") // localhost:8080/api
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello kotlin";
    }

    // HTTP 메소드와 별개로 호출 메소드에 종속된다.
    @RequestMapping(method = [RequestMethod.GET], path = ["request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/steve/27
    fun pathVariable(@PathVariable name: String, @PathVariable age:Int): String {
        println("${name}, ${age}")
        return "pathVar $name $age"
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable2/steve/27
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age:Int): String {
        val name = "kotlin"

        println("${_name}, $age")
        return "pathVar2 $_name $age"
    }

    @GetMapping("/get-mapping/query-param")     // GET http://localhost:8080/api/get-mapping/query-param?name=steve&age=27
    fun queryParam(@RequestParam(name = "name") name: String, @RequestParam(value = "age") age: Int): String {
        println("$name, $age")
        return "query $name $age"
    }

    // name, age, address, email
    @GetMapping("/get-mapping/query-param/object") // GET http://localhost:8080/api/get-mapping/query-param/object
    // http://localhost:8080/api/get-mapping/query-param/object?name=steve&age=10&email=uiurihappy@gmail.com&address=seoul
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map") // GET http://localhost:8080/api/get-mapping/query-param/map
    // http://localhost:8080/api/get-mapping/query-param/map?name=steve&age=10&email=uiurihappy@gmail.com&address=seoul
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        println(phoneNumber)
        return map
    }
}