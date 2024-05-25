package com.example.smarttrade.auth.presentation.validation

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    fun execute(email: String): ValidationResult {
        if (email.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Debes añadir un email"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email no válido"
            )
        }
        return ValidationResult(successful = true)
    }
}