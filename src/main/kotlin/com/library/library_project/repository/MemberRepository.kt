package com.library.library_project.repository

import com.library.library_project.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmailAndIdNot(email: String,id: Long): Member?
    fun findByEmail(email: String): Member?
}