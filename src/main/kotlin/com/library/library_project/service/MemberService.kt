package com.library.library_project.service

import com.library.library_project.component.sortByMulti
import com.library.library_project.exception.ResourceNotFoundException
import com.library.library_project.dto.request.MemberRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.dto.response.DataResponse
import com.library.library_project.dto.response.PaginationResponse
import com.library.library_project.dto.response.Status
import com.library.library_project.mapper.toMember
import com.library.library_project.model.Member
import com.library.library_project.repository.MemberRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class MemberService(private val repository: MemberRepository){
    fun create(member: MemberRequest): Member {
        val existingEmail = repository.findByEmail(member.email)
        if (existingEmail != null) {
            throw ResourceNotFoundException("Email already exists")
        }
        return repository.save(member.toMember())
    }

    fun update(id:Long, member: MemberRequest): Member {
        val foundMember = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Member with id $id not found") }

        val existingEmail = repository.findByEmailAndIdNot(member.email,id)
        if (existingEmail != null) {
            throw ResourceNotFoundException("Email already exists")
        }
        foundMember?.let {
            it.name = member.name
            it.email = member.email
            it.phone = member.phone
            it.address = member.address
        }
        return repository.save(foundMember)
    }
    fun findAll(
        currentPage: Int,
        pageSize: Int,
        sortBy: List<String>?
    ): ApiResponse<Member>{
        val order = sortBy?.sortByMulti()?: listOf(Sort.Order.asc("id"))
        val sort = Sort.by(order)
        val pageable = PageRequest.of(currentPage - 1, pageSize, sort)
        val result = repository.findAll(pageable)
        return ApiResponse(
            Status(
                codeError = 1,
                message = "Success hz",
            ),
            DataResponse(
                content = result.content,
                pagination = PaginationResponse(
                    currentPage = currentPage,
                    pageSize = result.size,
                    totalElements = result.totalElements,
                    totalPage = result.totalPages,
                )
            )
        )
    }
}