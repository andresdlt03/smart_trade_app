package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

class ValidatePassword @Inject constructor() {
    fun execute(password: String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Debes añadir una contraseña"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "La contraseña debe tener al menos 6 caracteres"
            )
        }

        return ValidationResult(successful = true)
    }
}