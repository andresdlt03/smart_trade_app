package com.example.smarttrade.auth.http.login

data class LoginFailed (
    val errorMessage: String? = null,
    val email: String? = null
)