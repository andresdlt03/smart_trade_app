package com.example.smarttrade.auth.presentation.viewmodel.state

data class ClientRegisterState (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val dni: String = "",
    val nameError: String? = null,
    val surnameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val dniError: String? = null,
)