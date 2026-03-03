package com.library.library_project.mapper

import com.library.library_project.dto.request.BookRequest
import com.library.library_project.model.Book
import org.springframework.data.jpa.domain.AbstractPersistable_.id

fun BookRequest.toBook(): Book= Book(
    title = title,
    author = author,
    isbn = isbn,
    stock = stock,
    categoryId = categoryId
)