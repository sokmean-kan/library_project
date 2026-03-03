package com.library.library_project.controller

import com.library.library_project.dto.request.BorrowingRequest
import com.library.library_project.dto.request.PaginationRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.dto.response.BorrowingResForm
import com.library.library_project.dto.response.BorrowingResponse
import com.library.library_project.dto.response.BorrowingResponseDetail
import com.library.library_project.model.Borrowing
import com.library.library_project.service.BorrowingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/borrowing")
class BorrowingController(private val borrowingService: BorrowingService) {
    @PostMapping("/create")
    fun createBorrowing(@RequestBody request: BorrowingRequest): ResponseEntity<BorrowingResForm> {
        val result = borrowingService.createBorrowing(request)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/list")
    fun getAllBorrowings(
        @RequestBody request: PaginationRequest
    ): ResponseEntity<ApiResponse<Borrowing>> {
        val result = borrowingService.getAllBorrowings(request.currentPage,request.pageSize,request.sortBy)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/detail/{id}")
    fun getBorrowingDetails(
        @PathVariable id: Long
    ): ResponseEntity<BorrowingResponseDetail>{
        val result = borrowingService.getDetailBorrowing(id)
        return ResponseEntity.ok(result)
    }
}