package com.example.smarttrade.product_management.presentation.validation

import javax.inject.Inject

class ValidateExtraFields @Inject constructor(){
    fun execute(field: String): ValidationResult {
        if (field.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Este campo no puede ser vacío"
            )
        }
        if (field.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = "Este campo es muy largo"
            )
        }

        return ValidationResult(successful = true)
    }
}