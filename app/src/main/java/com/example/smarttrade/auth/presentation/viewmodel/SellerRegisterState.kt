package com.example.smarttrade.auth.presentation.viewmodel

data class SellerRegisterState (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val companyName: String = "",
    val cif: String = ""
)