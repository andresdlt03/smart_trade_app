package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

class ValidateNotEmpty @Inject constructor() {
    fun execute(value: String): ValidationResult {
        if(value.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "This field cannot be empty"
            )
        }
        return ValidationResult(successful = true)
    }
}