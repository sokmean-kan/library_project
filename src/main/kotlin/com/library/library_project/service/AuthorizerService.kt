package com.library.library_project.service

import com.library.library_project.dto.request.ReturnedApprovalRequest
import com.library.library_project.dto.request.SetDueDateRequest
import com.library.library_project.dto.request.UpdateStatusRequest
import com.library.library_project.dto.response.Approved
import com.library.library_project.dto.response.Status
import com.library.library_project.dto.response.UpdateStatusResponse
import com.library.library_project.exception.ResourceNotFoundException
import com.library.library_project.repository.*
import org.springframework.stereotype.Service

@Service
class AuthorizerService(
    private val authorizerRepository: AuthorizerRepository,
    private val borrowingRepository: BorrowingRepository,
    private val statusRepository: StatusRepository,
    private val bookRepository: BookRepository,
    private val memberRepository: MemberRepository
) {
    fun updateStatus(id:Long,request: UpdateStatusRequest): UpdateStatusResponse {
        val foundBorrowing = borrowingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }

        val foundBook = bookRepository.findById(foundBorrowing.bookId).get()

        val memberFound = memberRepository.findById(foundBorrowing.memberId).get()

        val foundStatus = statusRepository.findById(request.statusId).orElseThrow{ ResourceNotFoundException("Not found status with id ${request.statusId}") }

        val approvedBy = authorizerRepository.findById(request.authorizerId).get()
        foundBorrowing.statusId = request.statusId
        foundBorrowing.authorizerId = request.authorizerId
        borrowingRepository.save(foundBorrowing)
        return UpdateStatusResponse(
            Status(
                message = "Updated status to ${foundStatus.name}",
            ),
            borrowing = Approved(
                bookTitle = foundBook.title?:"Unknown",
                borrowerName = memberFound.name?:"Unknown",
                approvedBy = approvedBy.name?:"Unknown",

            )
        )
    }

    fun setDueDate(id: Long, request: SetDueDateRequest): Status {
        val foundBorrowing = borrowingRepository.findById(id).orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }
        val foundBook = bookRepository.findById(foundBorrowing.bookId).get()
        foundBorrowing.dueDate = request.dueDate
        borrowingRepository.save(foundBorrowing)
        return Status(
            message = "Due date of borrowing book: ${foundBook.title} is ${foundBorrowing.dueDate}",
        )
    }

    fun returnedApprovalRequest(id: Long, request: ReturnedApprovalRequest): Status {
        val foundBorrowing = borrowingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Not found borrowing with id $id") }
        foundBorrowing.returnedAt = request.returnedDate
        foundBorrowing.authorizerId = request.authorizerId
        borrowingRepository.save(foundBorrowing)
        return Status(
            message = "Returned approval at ${request.returnedDate}"
        )
    }
}