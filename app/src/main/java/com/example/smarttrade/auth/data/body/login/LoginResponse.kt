package com.example.smarttrade.auth.data.body.login

sealed class LoginResponse {
    val loginCredentials: LoginCredentials? = null
    val errorMessage: String? = null
}