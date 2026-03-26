package com.library.library_project.service

import com.library.library_project.component.sortPagination
import com.library.library_project.dto.request.BookRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.dto.response.DataResponse
import com.library.library_project.dto.response.PaginationResponse
import com.library.library_project.dto.response.Status
import com.library.library_project.exception.ResourceNotFoundException
import com.library.library_project.mapper.toBook
import com.library.library_project.model.Book
import com.library.library_project.repository.BookRepository
import com.library.library_project.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val categoryRepository: CategoryRepository,
) {

    fun createBook( request: BookRequest): Book{
        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow{ ResourceNotFoundException("Category Not Found") }
        val result = bookRepository.save(request.toBook())
        return result
    }

//    fun sortPagination(
//        sortBy: List<String>?,
//        currentPage: Int,
//        pageSize: Int): PageRequest{
//        val order = sortBy?.sortByMulti()?: listOf(Sort.Order.asc("id"))
//        val sort = Sort.by(order)
//       return PageRequest.of(currentPage - 1, pageSize, sort)
//    }

    fun getAllBooks(
        currentPage: Int,
        pageSize: Int,
        sortBy: List<String>?
    ): ApiResponse<Book> {
        val result = bookRepository.findAll(sortPagination(sortBy, currentPage, pageSize))
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