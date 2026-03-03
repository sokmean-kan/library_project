package com.library.library_project.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_id_generator", sequenceName = "books_id_seq", allocationSize = 50, initialValue = 11)
    val id: Long = 0,
    val title: String? = "",
    val author: String? = "",
    val isbn: String? = "",
    val stock: Int? = 0,
    val categoryId: Int? = 0,
)