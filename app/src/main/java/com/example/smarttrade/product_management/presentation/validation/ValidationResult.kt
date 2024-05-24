package com.example.smarttrade.product_management.presentation.validation;

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

