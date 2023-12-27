package com.kotlinMVC.controller.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kotlinMVC.model.http.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc:  MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect {
            status().isOk
        }.andExpect(
            content().string("hello")
        ).andDo(print())
    }

//    @Test
//    fun helloTest_Bad_Request() {
//        mockMvc.perform(
//            get("/api/exception/hello")
//        ).andExpect (
//            status().isBadRequest
//        ).andExpect(
//            content().string("helre")
//        ).andDo(print())
//    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "ybchar")
        queryParams.add("age", "28")
        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect (
            status().isOk
        ).andExpect(
            content().string("ybchar 28")
        ).andDo(print())
    }

    @Test
    fun getTest_Fail() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "ybchar")
        queryParams.add("age", "9")
        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        )
            .andDo(print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "ybchar"
            this.age = 28
            this.address = "seoul"
            this.phoneNumber = "010-3735-2396"
            this.email = "uiurihappy@naver.com"
            this.createdAt = "2023-12-01 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)
        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("ybchar")
        ).andExpect(
            jsonPath("\$.age").value("28")
        ).andExpect(
            jsonPath("\$.address").value("seoul")
        )
            .andDo(print())
    }

    @Test
    fun postTest_Fail() {
        val userRequest = UserRequest().apply {
            this.name = "ybchar_test"
            this.age = 28
            this.address = "seoul"
            this.phoneNumber = "010-3735-2396"
            this.email = "uiurihappy@naver.com"
            this.createdAt = "2023-12-01 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)
        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            jsonPath("\$.errors[0].field").value("name")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("ybchar_test")
        ).andExpect(
            jsonPath("\$.errors[0].message").value("이름은 2 이상, 8 이하")
        ).andDo(print())
    }
}