package com.library.library_project.mapper

import com.library.library_project.dto.request.CreateAuthorizerRequest
import com.library.library_project.model.Authorizer
import java.time.LocalDateTime

fun CreateAuthorizerRequest.toAuthorizer(): Authorizer {
    return Authorizer(
        name = name,
        email = email,
        phone = phone,
        createdAt = LocalDateTime.now(),
    )
}