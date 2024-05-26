package com.example.smarttrade.product_management.presentation.validation

import javax.inject.Inject

class ValidateStock @Inject constructor() {
    fun execute(stock: String): ValidationResult {
        if(stock.toIntOrNull() == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "El stock debe ser un número entero"
            )
        }
        return ValidationResult(successful = true)
    }
}