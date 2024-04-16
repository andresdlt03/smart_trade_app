package com.example.smarttrade.auth.presentation.viewmodel.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    var loginError: String? = null,
    val loginSuccess: Boolean = false
)