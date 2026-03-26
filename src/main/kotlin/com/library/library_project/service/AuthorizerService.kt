package com.library.library_project.service

import com.library.library_project.dto.request.ApprovalBorrowRequest
import com.library.library_project.dto.request.CreateAuthorizerRequest
import com.library.library_project.dto.request.ReturnedApprovalRequest
import com.library.library_project.dto.response.Approved
import com.library.library_project.dto.response.Status
import com.library.library_project.dto.response.UpdateStatusResponse
import com.library.library_project.exception.ResourceNotFoundException
import com.library.library_project.mapper.toAuthorizer
import com.library.library_project.model.Authorizer
import com.library.library_project.repository.*
import org.springframework.stereotype.Service

@Service
class AuthorizerService(
    private val authorizerRepository: AuthorizerRepository,
    private val borrowingRepository: BorrowingRepository,
    private val bookRepository: BookRepository,
    private val memberRepository: MemberRepository
) {
    //create new authorizer
    fun createAuthorizer(request: CreateAuthorizerRequest): Authorizer {
//        val user = authorizerRepository.existsByEmail(request.email) // Already created annotation UniqueEmail
//        if (user) {
//            throw ResourceNotFoundException("Email already exists")
//        }
        val result = authorizerRepository.save(request.toAuthorizer())
        return result
    }
    fun updateStatusToOverDue(id:Long): Status {
        val foundBorrowing = borrowingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }

        val foundBook = bookRepository.findById(foundBorrowing.bookId).get()

        val borrowerFound = memberRepository.findById(foundBorrowing.memberId).get()

//        val foundStatus = statusRepository.findById(request.statusId).orElseThrow{ ResourceNotFoundException("Not found status with id ${request.statusId}") }

//        val approvedBy = authorizerRepository.findById(request.authorizerId).orElseThrow { ResourceNotFoundException("Not found authorizer with id ${request.authorizerId}") }
        foundBorrowing.statusId = 4
//        foundBorrowing.authorizerId = approvedBy.id
        borrowingRepository.save(foundBorrowing)
        return Status(
            message = """Book:"${foundBook.title}" borrowed by "${borrowerFound.name}" was over due dates!""",
        )
    }

    fun approvalBorrowing(id: Long, request: ApprovalBorrowRequest): UpdateStatusResponse {
        val foundBorrowing = borrowingRepository.findById(id).orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }
        val foundBook = bookRepository.findById(foundBorrowing.bookId).get()
        val memberFound = memberRepository.findById(foundBorrowing.memberId).get()
        val approvedBy = authorizerRepository.findById(request.authorizerId).orElseThrow { ResourceNotFoundException("Not found authorizer with id ${request.authorizerId}") }

        if (foundBorrowing.statusId == 2){
            throw ResourceNotFoundException("The borrowing has already been approved")
        }else if(foundBorrowing.statusId == 3){
            throw ResourceNotFoundException("The borrowing has returned")
        }
        foundBorrowing.dueDate = request.dueDate
        foundBorrowing.statusId = 2
        foundBorrowing.authorizerId = approvedBy.id
        borrowingRepository.save(foundBorrowing)
        return UpdateStatusResponse(
            Status(
                message = "Borrowing approved",
            ),
            borrowing = Approved(
                bookTitle = foundBook.title?:"Unknown",
                borrowerName = memberFound.name?:"Unknown",
                approvedBy = approvedBy.name?:"Unknown",

                )
        )
    }

    fun returnedApprovalRequest(id: Long, request: ReturnedApprovalRequest): Status {
        val foundBorrowing = borrowingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }
        foundBorrowing.returnedAt = request.returnedDate
        val receiver = authorizerRepository.findById(request.receiverId).orElseThrow { ResourceNotFoundException("Not found receiver with id ${request.receiverId}") }
        foundBorrowing.receiverId = receiver.id
        if (foundBorrowing.statusId ==1){
            throw ResourceNotFoundException("Not approved yet")
        }else if (foundBorrowing.statusId ==3){
            throw ResourceNotFoundException("Already returned")
        }
        foundBorrowing.statusId = 3
        borrowingRepository.save(foundBorrowing)
        return Status(
            message = "Returned approval at ${request.returnedDate}"
        )
    }
}