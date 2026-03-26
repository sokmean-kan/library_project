package com.library.library_project.controller

import com.library.library_project.dto.request.ApprovalBorrowRequest
import com.library.library_project.dto.request.CreateAuthorizerRequest
import com.library.library_project.dto.request.ReturnedApprovalRequest
import com.library.library_project.dto.request.UpdateStatusRequest
import com.library.library_project.dto.response.Status
import com.library.library_project.dto.response.UpdateStatusResponse
import com.library.library_project.service.AuthorizerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorizer")
class AuthorizerController(private val authorizerService: AuthorizerService) {

    @PostMapping("/add")
    fun createAuthorizer(
       @Valid @RequestBody request: CreateAuthorizerRequest
    ) = authorizerService.createAuthorizer(request)

    @PostMapping("/status/{id}")
    fun updateStatus(
        @PathVariable id: Long,
//        @RequestBody updateStatusRequest: UpdateStatusRequest
    ): ResponseEntity<Status> {
        val response = authorizerService.updateStatusToOverDue(id)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/setDueDate/{id}")
    fun setDueDate(
        @PathVariable id: Long,
        @RequestBody approvalRequest: ApprovalBorrowRequest
    ): ResponseEntity<UpdateStatusResponse> {
        val response = authorizerService.approvalBorrowing(id,approvalRequest)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/returned/{id}")
    fun returnedApprovalDate(
        @PathVariable id: Long,
        @RequestBody request: ReturnedApprovalRequest
    ): ResponseEntity<Status> {
        val result = authorizerService.returnedApprovalRequest(id, request)
        return ResponseEntity.ok(result)
    }

}