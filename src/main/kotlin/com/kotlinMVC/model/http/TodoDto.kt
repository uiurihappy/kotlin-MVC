package com.kotlinMVC.model.http

import com.kotlinMVC.annotation.StringFormatDateTime
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class TodoDto(
    var index: Int? = null,

    @field:NotBlank
    var title: String? = null,

    var description: String? = null,

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    // yyyy-MM-dd HH:mm:ss
    var schedule: String? = null,

    var createdAt: LocalDateTime? = null,

    var updatedAt: LocalDateTime? = null
) {
}