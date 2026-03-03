package com.library.library_project.mapper

import com.library.library_project.dto.request.BorrowingRequest
import com.library.library_project.dto.response.BorrowingResForm
import com.library.library_project.dto.response.BorrowingResponse
import com.library.library_project.dto.response.BorrowingResponseDetail
import com.library.library_project.dto.response.Status
import com.library.library_project.model.Authorizer
import com.library.library_project.model.Book
import com.library.library_project.model.Borrowing
import com.library.library_project.model.Member
import java.math.BigDecimal
import java.time.LocalDateTime

fun BorrowingRequest.toBorrowing(): Borrowing =
    Borrowing(
        memberId = memberId,
        bookId = bookId,
        borrowedAt = LocalDateTime.now(),
        dueDate = LocalDateTime.now(),
        returnedAt = null,
        fine = fine,

    )

//fun Borrowing.toBorrowResponse(): BorrowingResponse =
//    BorrowingResponse(
//        id = id,
//        memberId = memberId,
//        bookId = bookId,
//        borrowedAt = borrowedAt,
//        dueDate = dueDate,
//        returnedAt = returnedAt,
//        fine = fine,
//        statusId = statusId,
//        authorizerId = authorizerId,
//
//    )
fun Borrowing.toBorrowResponseDetail(member: Member, book: Book, status: com.library.library_project.model.Status, authorizer: Authorizer?): BorrowingResponseDetail =
    BorrowingResponseDetail(
        id = this.id,
        member = member,
        book = book,
        status = status,
        authorizer = authorizer,
        borrowedAt = this.borrowedAt,
        returnedAt = this.returnedAt,
        dueDate = this.dueDate,
        fine = this.fine,

    )

fun Borrowing.toBorrowingResForm(): BorrowingResForm =
    BorrowingResForm(
        status = Status(
            message = "success",
        ),
        borrowing = BorrowingResponse(
            id = this.id,
            memberId = this.memberId,
            bookId = this.bookId,
            statusId = this.statusId,
            authorizerId = this.authorizerId,
            borrowedAt = this.borrowedAt,
            returnedAt = this.returnedAt,
            dueDate = this.dueDate,
            fine = this.fine,
        )
    )