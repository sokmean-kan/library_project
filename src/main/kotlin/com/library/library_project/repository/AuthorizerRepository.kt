package com.library.library_project.repository

import com.library.library_project.model.Authorizer
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorizerRepository : JpaRepository<Authorizer, Long>