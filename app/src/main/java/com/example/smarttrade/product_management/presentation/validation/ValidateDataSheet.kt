package com.example.smarttrade.product_management.presentation.validation

class ValidateDataSheet {
    fun execute(dataSheet: String): ValidationResult {
        if (dataSheet.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "La ficha técnica no puede ser vacía"
            )
        }
        if (dataSheet.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = "La ficha técnica es muy larga"
            )
        }

        return ValidationResult(successful = true)
    }
}