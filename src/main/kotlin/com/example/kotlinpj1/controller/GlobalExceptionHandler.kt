package com.example.kotlinpj1.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(
    val error: String,
    val message: String
)

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val message = e.constraintViolations.joinToString(", ") { it.message }
        return ResponseEntity.badRequest().body(ErrorResponse("Bad Request", message))
    }
}
