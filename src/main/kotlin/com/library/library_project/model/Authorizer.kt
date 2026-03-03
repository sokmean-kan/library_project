package com.library.library_project.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "authorizers")
data class Authorizer(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "authorizer_id_seq", sequenceName = "authorizers_id_seq", allocationSize = 30, initialValue = 4)
    var id: Long = 0,
    var name: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var createdAt: LocalDateTime? = LocalDateTime.now(),

)