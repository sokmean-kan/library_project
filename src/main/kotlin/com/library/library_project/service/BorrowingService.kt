package com.library.library_project.service

import com.library.library_project.component.sortByMulti
import com.library.library_project.dto.request.BorrowingRequest
import com.library.library_project.dto.response.*
import com.library.library_project.exception.ResourceNotFoundException
import com.library.library_project.mapper.toBorrowResponseDetail
import com.library.library_project.mapper.toBorrowing
import com.library.library_project.mapper.toBorrowingResForm
import com.library.library_project.model.Borrowing
import com.library.library_project.repository.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BorrowingService(
    private val borrowingRepository: BorrowingRepository,
    private val memberRepository: MemberRepository,
    private val bookRepository: BookRepository,
    private val statusRepository: StatusRepository,
    private val authorizerRepository: AuthorizerRepository
) {
    fun createBorrowing(request: BorrowingRequest): BorrowingResForm {
        val result = borrowingRepository.save(request.toBorrowing())
        val lastResult = result.toBorrowingResForm()
        return lastResult
    }

    fun getAllBorrowings(
        currentPage: Int,
        pageSize: Int,
        sortBy: List<String>?
    ): ApiResponse<Borrowing> {
        val order = sortBy?.sortByMulti() ?: listOf(Sort.Order.asc("id"))
        val sort = Sort.by(order)
        val pageable = PageRequest.of(currentPage - 1, pageSize, sort)
        val result = borrowingRepository.findAll(pageable)
//        val listBorrowings = result.content.map { borrow ->
//            val member = memberRepository.findById(borrow.memberId).orElseThrow {
//                IllegalArgumentException("Unit not found with id ${borrow.memberId}")
//            }
//            val book = bookRepository.findById(borrow.bookId).orElseThrow {
//                ResourceNotFoundException("not found")
//            }
//            borrow.toBorrowResponseDetail(member,book)
//        }
        return ApiResponse(
            Status(
                message = "Success hz"
            ),
            DataResponse(
                content = result.content,
                pagination = PaginationResponse(
                    currentPage = currentPage,
                    pageSize = pageSize,
                    totalElements = result.totalElements,
                    totalPage = result.totalPages
                )
            )
        )
    }

    fun getDetailBorrowing(id: Long): BorrowingResponseDetail {
        val result = borrowingRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Borrowing id $id not found") }
        val member = memberRepository.findById(result.memberId)
            .orElseThrow { ResourceNotFoundException("Member id ${result.memberId} does not exist") }
        val book = bookRepository.findById(result.bookId)
            .orElseThrow { ResourceNotFoundException("Book id ${result.bookId} not found") }
        val status = statusRepository.findById(result.statusId?:1)
            .orElseThrow { ResourceNotFoundException("Status id ${result.statusId} not found") }

        val authorizerId = result.authorizerId
        val authorizer = if (authorizerId != null){
              authorizerRepository.findById(authorizerId).get()
        }else null
//        OR
//        val authorizer = result.authorizerId?.let { authorizerId ->
//            authorizerRepository.findById(authorizerId).get()
//        }
        return result.toBorrowResponseDetail(member, book, status, authorizer)
    }
}