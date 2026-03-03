package com.library.library_project.dto.request

data class MemberRequest(
    val name: String,
    val email: String,
    val phone: String,
    val address: String
)