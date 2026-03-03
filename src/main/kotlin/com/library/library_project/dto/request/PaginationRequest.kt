package com.library.library_project.dto.request

data class PaginationRequest(
    val currentPage: Int,
    val pageSize: Int,
    val sortBy: List<String>,
)