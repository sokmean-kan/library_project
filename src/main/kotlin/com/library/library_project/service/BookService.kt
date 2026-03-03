package com.library.library_project.service

import com.library.library_project.component.sortByMulti
import com.library.library_project.dto.request.BookRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.dto.response.DataResponse
import com.library.library_project.dto.response.PaginationResponse
import com.library.library_project.dto.response.Status
import com.library.library_project.mapper.toBook
import com.library.library_project.model.Book
import com.library.library_project.repository.BookRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun createBook( request: BookRequest): Book{
        val result = bookRepository.save(request.toBook())
        return result
    }

    fun getAllBooks(
        currentPage: Int,
        pageSize: Int,
        sortBy: List<String>?
    ): ApiResponse<Book> {
        val order = sortBy?.sortByMulti()?: listOf(Sort.Order.asc("id"))
        val sort = Sort.by(order)
        val pageable = PageRequest.of(currentPage - 1, pageSize, sort)
        val result = bookRepository.findAll(pageable)
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

}