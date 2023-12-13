package com.kotlinMVC.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

// class에 타입을 지정, 그런데 곧 Deprecated...?
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(
    var name:String?=null,
    var age:Int?=null,
    var email:String?=null,
    var address:String?=null,
    // snake-case 인 경우 매칭시켜주는 방법으로, JsonProperty가 존재
//    @JsonProperty("phone_number")
    var phoneNumber:String?=null,
)