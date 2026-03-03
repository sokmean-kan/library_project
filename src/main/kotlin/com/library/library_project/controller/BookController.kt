package com.library.library_project.controller

import com.library.library_project.dto.request.BookRequest
import com.library.library_project.dto.request.PaginationRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.model.Book
import com.library.library_project.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(private val bookService: BookService) {
    @PostMapping("/create")
    fun createBook( @RequestBody request: BookRequest): Book{
        val result = bookService.createBook(request)
        return result
    }

    @PostMapping("/list")
    fun getAll(
        @RequestBody request: PaginationRequest
    ): ResponseEntity<ApiResponse<Book>>{
        val result = bookService.getAllBooks(request.currentPage, request.pageSize,request.sortBy)
        return ResponseEntity.ok(result)
    }
}