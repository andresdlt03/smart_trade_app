package com.example.smarttrade.product_management.presentation.validation

import com.example.smarttrade.auth.presentation.validation.ValidationResult
import javax.inject.Inject

class ValidatePrice @Inject constructor() {
    fun execute(price: String): ValidationResult {
        if(price.toDoubleOrNull() == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "El precio debe ser un número"
            )
        } else {
            return ValidationResult(
                successful = true
            )
        }
    }
}