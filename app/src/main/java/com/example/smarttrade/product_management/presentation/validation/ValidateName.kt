package com.example.smarttrade.product_management.presentation.validation;

import javax.inject.Inject

public class ValidateName @Inject constructor() {
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
