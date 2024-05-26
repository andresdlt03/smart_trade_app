package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

class ValidateNotEmpty @Inject constructor() {
    fun execute(value: String): ValidationResult {
        if(value.isEmpty()) {
            return ValidationResult(
                successful = false,
<<<<<<< HEAD
                errorMessage = "Este campo no puede estar vacío"
=======
                errorMessage = "Debes completar este campo"
>>>>>>> develop
            )
        }
        return ValidationResult(successful = true)
    }
}