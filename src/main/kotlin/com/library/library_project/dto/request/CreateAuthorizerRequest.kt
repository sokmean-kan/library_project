package com.library.library_project.dto.request

import com.library.library_project.validator.UniqueEmail
import java.time.LocalDateTime

data class CreateAuthorizerRequest(
    val name: String,
    @field:UniqueEmail
    val email: String,
    val phone: String,
//    val createAt: LocalDateTime
)