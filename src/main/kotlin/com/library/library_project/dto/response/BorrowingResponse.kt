package com.library.library_project.dto.response

import com.library.library_project.model.Authorizer
import com.library.library_project.model.Book
import com.library.library_project.model.Member
import java.math.BigDecimal
import java.time.LocalDateTime

data class BorrowingResponse(
    val id: Long,
    var memberId: Long,
    var bookId: Long,
    val borrowedAt: LocalDateTime?,
    val dueDate: LocalDateTime?,
    val returnedAt: LocalDateTime?,
    val fine: BigDecimal,
    val statusId: Int?,
    val authorizerId: Long?,
)
data class BorrowingResForm(
    val status: Status,
    val borrowing: BorrowingResponse
)
data class BorrowingResponseDetail(
    val id: Long,
    val status: com.library.library_project.model.Status,
    val authorizer: Authorizer?,
    var member: Member,
    var book: Book,
    val borrowedAt: LocalDateTime?,
    val dueDate: LocalDateTime?,
    val returnedAt: LocalDateTime?,
    val fine: BigDecimal
)