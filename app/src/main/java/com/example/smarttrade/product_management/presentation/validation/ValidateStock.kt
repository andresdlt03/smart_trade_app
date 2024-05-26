package com.example.smarttrade.product_management.presentation.validation

import com.example.smarttrade.auth.presentation.validation.ValidationResult
import javax.inject.Inject

class ValidateStock @Inject constructor() {
    fun execute(stock: String): ValidationResult {
        if(stock.toIntOrNull() == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "El stock debe ser un número entero"
            )
        } else {
            return ValidationResult(
                successful = true
            )
        }
    }
}