package com.example.smarttrade.auth.presentation.validation

import javax.inject.Inject

public class ValidateBankAccount @Inject constructor() {
    fun execute(account: String): ValidationResult {
        if (account.isEmpty()) {
            return ValidationResult(
                successful = false,
<<<<<<< HEAD
                errorMessage = "La cuenta bancaria no puede estar vacía"
=======
                errorMessage = "Debes añadir una cuenta bancaria"
>>>>>>> develop
            )
        }
        if (account.length != 24) {
            return ValidationResult(
                successful = false,
<<<<<<< HEAD
                errorMessage = "La cuenta bancaria debe tener 24 dígitos"
=======
                errorMessage = "Una cuenta bancaria tiene 24 dígitos"
>>>>>>> develop
            )
        }

        return ValidationResult(successful = true)
    }
}
