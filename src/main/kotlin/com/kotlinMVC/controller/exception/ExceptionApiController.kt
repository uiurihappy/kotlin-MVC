package com.kotlinMVC.controller.exception

import com.kotlinMVC.model.http.ErrorResponse
import com.kotlinMVC.model.http.Error
import com.kotlinMVC.model.http.UserRequest
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(): String {
        val list = mutableListOf<String>()
//        val temp = list[0]
        return "hello"
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 6)
        @RequestParam name:String,

        @Min(10)
        @RequestParam age:Int
    ): String {
        println(name)
        println(age)
        return "$name $age"
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        // bindingResult에서 모든 에러를 가져온다. (errors)
        e.bindingResult.allErrors.forEach { errorObj ->
            val error = Error().apply {
                // fieldName
                this.field = (errorObj as FieldError).field
                // default Message
                this.message = errorObj.defaultMessage
                // 어떠한 이유로 reject 되었는 지
                this.value = errorObj.rejectedValue
            }
            errors.add(error)
        }

        // 2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse>{
        // 1. 에러 분석
        val errors = mutableListOf<Error>()
        e.constraintViolations.forEach {
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }
            errors.add(error)
        }

        // 2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException):
//            String  // 200 OK
            ResponseEntity<String>
    {
        println("controller exception handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}