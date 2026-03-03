package com.library.library_project.dto.response

data class ApiResponse<T>(
    val status: Status,
    val data: DataResponse<T>?
)
data class DataResponse<T>(
    val content: List<T>,
    val pagination: PaginationResponse
)

data class PaginationResponse(
    val currentPage: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPage: Int
)