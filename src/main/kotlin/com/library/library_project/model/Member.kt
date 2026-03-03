package com.library.library_project.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "members")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "member_id_seq", sequenceName = "members_id_seq", allocationSize = 10, initialValue = 9)
    val id: Long = 0,
    var name: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var address: String? = "",
    val joinedAt: LocalDateTime? = LocalDateTime.now(), //Auto in database
)