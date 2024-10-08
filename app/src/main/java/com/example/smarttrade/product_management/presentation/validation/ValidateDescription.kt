package com.example.smarttrade.product_management.presentation.validation

import javax.inject.Inject

class ValidateDescription @Inject constructor() {
    fun execute(description: String): ValidationResult {
        if (description.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "La descripción no puede ser vacía"
            )
        }
        if (description.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = "La descripción es muy larga"
            )
        }

        return ValidationResult(successful = true)
    }
}