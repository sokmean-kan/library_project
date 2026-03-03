package com.library.library_project.repository

import com.library.library_project.model.Status
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository : JpaRepository<Status, Int>