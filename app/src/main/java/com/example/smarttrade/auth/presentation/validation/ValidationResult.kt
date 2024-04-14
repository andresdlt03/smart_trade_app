package com.example.smarttrade.auth.presentation.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
