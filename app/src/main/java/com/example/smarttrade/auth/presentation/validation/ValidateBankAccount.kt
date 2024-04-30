package com.example.smarttrade.auth.presentation.validation

import android.util.Patterns
import javax.inject.Inject

public class ValidateBankAccount @Inject constructor() {
    fun execute(account: String): ValidationResult {
        if (account.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Bank account cannot be empty"
            )
        }
        if (account.length != 24) {
            return ValidationResult(
                successful = false,
                errorMessage = "Bank account must be 24 characters long"
            )
        }

        return ValidationResult(successful = true)
    }
}
