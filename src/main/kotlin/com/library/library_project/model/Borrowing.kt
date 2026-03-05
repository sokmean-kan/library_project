package com.library.library_project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "borrowings")
data class Borrowing(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "borrowing_id_generator", sequenceName = "borrowings_id_seq", allocationSize = 10, initialValue = 11)
    val id: Long = 0,
    val memberId: Long = 0,
    val bookId: Long = 0,
    val borrowedAt: LocalDateTime?,
    var dueDate: LocalDateTime?,
    var returnedAt: LocalDateTime?,
    val fine: BigDecimal,
    var statusId: Int? = 1,
    var authorizerId: Long? = null,
    var receiverId: Long? = null,
)