package com.kotlinMVC.advice

import com.kotlinMVC.controller.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e : RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException):
//            String  // 200 OK
              ResponseEntity<String>
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}