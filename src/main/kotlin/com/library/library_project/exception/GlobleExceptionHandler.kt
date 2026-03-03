package com.library.library_project.exception

import com.library.library_project.dto.response.ErrorResponse
import com.library.library_project.dto.response.FieldError
import com.library.library_project.dto.response.Status
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    // Handles @Valid failures in @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.map {
            FieldError(
                field = it.field,
                message = it.defaultMessage ?: "Invalid value"
            )
        }

        val response = ErrorResponse(
            status = Status(codeError = 1, message = "Invalid values bruh!"),
            data = errors//.first()
        )
        return ResponseEntity.badRequest().body(response)
    }

    // Optional: for other validation exceptions
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val errors = ex.constraintViolations.map {
            FieldError(
                field = it.propertyPath.toString(),
                message = it.message
            )
        }
        return ResponseEntity.badRequest().body(ErrorResponse(
            status = Status(codeError = 2, message = "Invalid value"),
            data = errors
        ))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = Status(codeError = 3, message = ex.message ?: "Ruk ot see"),
//            data = null
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)  // 404
    }

}