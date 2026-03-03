package com.library.library_project.controller

import com.library.library_project.dto.request.MemberRequest
import com.library.library_project.dto.request.PaginationRequest
import com.library.library_project.dto.response.ApiResponse
import com.library.library_project.model.Member
import com.library.library_project.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @PostMapping
    fun createMember(@RequestBody memberRequest: MemberRequest): ResponseEntity<Member> {
        val result = memberService.create(memberRequest)
        return ResponseEntity(result, HttpStatus.CREATED)
    }

    @PostMapping("/{id}")
    fun updateMember(
        @PathVariable id:Long,
        @RequestBody memberRequest: MemberRequest
    ): ResponseEntity<Member> {
        val update = memberService.update(id,memberRequest)
        return ResponseEntity(update, HttpStatus.OK)
    }

    @PostMapping("/list")
    fun getAll(@RequestBody request: PaginationRequest
    ): ApiResponse<Member> {
        val result = memberService.findAll(request.currentPage, request.pageSize, request.sortBy)
        return result
    }
}