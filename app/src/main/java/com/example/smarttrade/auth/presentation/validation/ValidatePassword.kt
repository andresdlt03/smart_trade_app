package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

class ValidatePassword @Inject constructor() {
    fun execute(password: String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be empty"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 6 characters long"
            )
        }

        return ValidationResult(successful = true)
    }
}