package com.example.smarttrade.product_management.presentation.validation

public class ValidatePhotos {
    fun execute(photos: List<String>): ValidationResult {
        if(photos.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Añade al menos una foto"
            )
        }
        return ValidationResult(successful = true)
    }
}