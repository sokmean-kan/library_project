package com.library.library_project.dto.request

data class BookRequest (
    var title: String,
    var author: String,
    var isbn: String,
    var stock: Int,
    var categoryId: Int,
)