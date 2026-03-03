package com.library.library_project.service

import com.library.library_project.component.sortByMulti
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.dto.response.DataResponse
import com.library.library_project.dto.response.PaginationResponse
import com.library.library_project.dto.response.Status
import com.library.library_project.model.Category
import com.library.library_project.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findAll(
        currentPage: Int,
        pageSize: Int,
        sortBy: List<String>?,
    ): ApiResponse<Category> {
        val order = sortBy?.sortByMulti()?: listOf(Sort.Order.asc("id"))
        val sort = Sort.by(order)
        val pageable = PageRequest.of(currentPage - 1, pageSize, sort)
        val result = categoryRepository.findAll(pageable)
        return ApiResponse(
            Status(
                message = "Success",
            ),
            DataResponse(
                content = result.content,
                pagination = PaginationResponse(
                    currentPage = currentPage,
                    pageSize = pageSize,
                    totalElements = result.totalElements,
                    totalPage = pageable.pageNumber,
                )
            )
        )
    }

    fun createCategory( name : String ): Category {
        val category = Category(name = name)
        return categoryRepository.save(category)
    }
}