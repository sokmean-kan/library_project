package com.library.library_project.dto.response

data class UpdateStatusResponse(
    val status: Status,
    val borrowing: Approved
)

data class Approved(
    val bookTitle: String,
    val borrowerName: String,
    val approvedBy: String,
)