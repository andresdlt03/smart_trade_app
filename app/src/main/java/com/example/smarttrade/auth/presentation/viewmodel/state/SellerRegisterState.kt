package com.example.smarttrade.auth.presentation.viewmodel.state

data class SellerRegisterState (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val companyName: String = "",
    val cif: String = "",
    val nameError: String? = null,
    val surnameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val companyNameError: String? = null,
    val cifError: String? = null

)