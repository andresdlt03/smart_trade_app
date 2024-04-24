package com.example.smarttrade.auth.presentation.viewmodel.state

data class ClientRegisterState (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val dni: String = "",
    val deliverDir:String = "",
    val factDir: String = "",
    val creditCard: String = "",
    val nameError: String? = null,
    val surnameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val dniError: String? = null,
    val deliverDirError: String? = null,
    val factDirError: String? = null,
    val creditCardError: String? = null,
    val registerError: String? = null,
    val registerSuccess: Boolean = false
)