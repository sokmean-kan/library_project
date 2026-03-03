package com.library.library_project.mapper

import com.library.library_project.dto.request.MemberRequest
import com.library.library_project.model.Member

fun MemberRequest.toMember(): Member = Member(
    name = name,
    email = email,
    phone = phone,
    address = address
)