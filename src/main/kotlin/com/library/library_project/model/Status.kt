package com.library.library_project.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.SequenceGenerators
import jakarta.persistence.Table

@Entity
@Table(name = "borrow_statuses")
data class Status(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "borrow_status_id_seq", sequenceName = "borrow_statuses_id_seq", allocationSize = 1, initialValue = 5)
    val id: Int = 0,
    val name: String? = "",

)