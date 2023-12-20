package com.kotlinMVC.controller.delete

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
// Bean 검증
@Validated
class DeleteApiController {

    // 1. Path Variable
    // 2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
            // 이름이 서로 다 달라지면 _ 사용
            @RequestParam(value = "name") _name: String,

            @NotNull(message = "age 값이 누락되었습니다.")
            @Min(value = 20, message = "age는 20보다 커야 합니다.")
            @RequestParam(value = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
            @PathVariable(value = "name") _name: String,
            @PathVariable(value = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }
}