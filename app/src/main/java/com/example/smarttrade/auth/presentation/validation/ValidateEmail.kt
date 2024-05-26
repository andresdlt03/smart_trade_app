package com.example.smarttrade.auth.presentation.validation

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    fun execute(email: String): ValidationResult {
        if (email.isEmpty()) {
            return ValidationResult(
                successful = false,
<<<<<<< HEAD
                errorMessage = "El email no puede estar vacío"
=======
                errorMessage = "Debes añadir un email"
>>>>>>> develop
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
<<<<<<< HEAD
                errorMessage = "El email no es válido"
=======
                errorMessage = "Email no válido"
>>>>>>> develop
            )
        }
        return ValidationResult(successful = true)
    }
}