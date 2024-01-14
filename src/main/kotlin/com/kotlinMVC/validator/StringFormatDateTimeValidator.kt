package com.kotlinMVC.validator

import com.kotlinMVC.annotation.StringFormatDateTime
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StringFormatDateTimeValidator : ConstraintValidator<StringFormatDateTime, String> {

    private var pattern: String ?= null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    // 정상이면 true 리턴
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        // 정상: true
        // 비정상: false

        return try {
//            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            LocalDateTime.parse(value, pattern?.let { DateTimeFormatter.ofPattern(it) })
            true
        } catch (e: Exception) {
            false
        }
    }
}