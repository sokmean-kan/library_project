package com.library.library_project.validator

import com.library.library_project.repository.AuthorizerRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component

@Component
class UniqueEmailValidator(
    private val authorizerRepository: AuthorizerRepository
) : ConstraintValidator<UniqueEmail, String> {

    override fun isValid(email: String?, context: ConstraintValidatorContext): Boolean {
        if (email.isNullOrBlank()) return true // let @NotBlank handle empty

        return !authorizerRepository.existsByEmail(email)
    }
}