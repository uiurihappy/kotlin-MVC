package com.kotlinMVC.model.http

import jakarta.validation.constraints.*
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// class에 타입을 지정, 그런데 곧 Deprecated...?
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(

    @field:NotEmpty
    @field:Size(min = 2, max = 8, message = "이름은 2 이상, 8 이하")
    var name:String ?= null,

    @field:PositiveOrZero // 0 < 숫자를 검증 0도 포함 (양수)
    var age:Int ?= null,

    @field:Email    // email 양식
    var email:String ?= null,

    @field:NotBlank // 공백을 검증
    var address:String ?= null,
    // snake-case 인 경우 매칭시켜주는 방법으로, JsonProperty가 존재
//    @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")  // 정규식 검증
    var phoneNumber:String ?= null,

    var createdAt:String ?= null    // yyyy-MM-dd HH:mm:ss ex) 2023-12-23 12:00:00
) {

    @AssertTrue(message = "생성 일자 패턴은 yyyy-MM-dd HH:mm:ss 여야 한다.")
    private fun isValidCreatedAt():Boolean {
        // 정상: true
        // 비정상: false

        return try {
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch (e:Exception) {
            false
        }
    }
}