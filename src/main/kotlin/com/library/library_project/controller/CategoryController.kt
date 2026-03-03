package com.library.library_project.controller

import com.library.library_project.dto.request.CategoryRequest
import com.library.library_project.dto.request.PaginationRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.model.Category
import com.library.library_project.service.CategoryService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService)
{
    @PostMapping("/list")
    fun getAllCategory(
        @RequestBody request: PaginationRequest
    ): ApiResponse<Category>{
        return categoryService.findAll(request.currentPage, request.pageSize, request.sortBy)
    }

    @PostMapping("/create")
    fun createCategory(
        @RequestBody request: CategoryRequest
    ):ResponseEntity<Category>{
        val result = categoryService.createCategory(request.name)
        return ResponseEntity.ok(result)
    }
}