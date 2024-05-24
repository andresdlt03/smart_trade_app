package com.example.smarttrade.product_management.presentation.validation;

public class ValidateName {
    fun execute(name: String): ValidationResult {
        if(name.isNullOrEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "El nombre no puede estar vacío"
            )
        }

        return ValidationResult(successful = true)
    }
}
