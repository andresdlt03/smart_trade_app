package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

public class ValidateBankAccount @Inject constructor() {
    fun execute(account: String): ValidationResult {
        if (account.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Debes añadir una cuenta bancaria"
            )
        }
        if (account.length != 24) {
            return ValidationResult(
                successful = false,
                errorMessage = "Una cuenta bancaria tiene 24 dígitos"
            )
        }

        return ValidationResult(successful = true)
    }
}
