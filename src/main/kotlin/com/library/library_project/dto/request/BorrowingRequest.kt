package com.library.library_project.dto.request

import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class BorrowingRequest(
    val memberId: Long,
    val bookId: Long,
//    @field:NotBlank(message = "duedate is mandatory")
//    var dueDate: LocalDateTime,
//    var returnedAt: LocalDateTime,
    var fine: BigDecimal,
    )