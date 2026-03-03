package com.library.library_project.dto.request

import com.library.library_project.model.Authorizer
import java.time.LocalDate
import java.time.LocalDateTime

data class UpdateStatusRequest(
    val authorizerId: Long,
    val statusId: Int,
)
data class SetDueDateRequest(
    val dueDate: LocalDateTime,
)
data class ReturnedApprovalRequest(
    val returnedDate: LocalDateTime?= LocalDateTime.now(),
    val authorizerId: Long
)