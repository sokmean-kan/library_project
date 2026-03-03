package com.library.library_project.repository

import com.library.library_project.model.Borrowing
import org.springframework.data.jpa.repository.JpaRepository

interface BorrowingRepository : JpaRepository<Borrowing, Long>