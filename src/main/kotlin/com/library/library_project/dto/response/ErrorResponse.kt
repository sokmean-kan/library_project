package com.library.library_project.dto.response


data class ErrorResponse(
    val status: Status,
    val data: List<FieldError> = emptyList(),
)

data class FieldError(
    val field: String,
    val message: String
)

data class Status(
    val codeError: Int?=0,
    val message: String
)


