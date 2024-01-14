package com.kotlinMVC.model.http

import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError

class TodoDtoTest {

    // 테스트 실행 후 검증
    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2020-10-10 13:00:00"
        }

        val result = validator.validate(todoDto)

        // result 결과 확인
        result.forEach{
            println(it.propertyPath.last().name)    // 필드명 확인
            println(it.message) // 에러 메세지 확인
            println(it.invalidValue) // 잘못된 값 확인
        }
        Assertions.assertEquals(true, result.isEmpty())
        println(result)
        println(result.isEmpty())

    }
}