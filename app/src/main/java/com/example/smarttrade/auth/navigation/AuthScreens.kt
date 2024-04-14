package com.example.smarttrade.auth.navigation

sealed class AuthScreens(val route: String) {
    data object Login : AuthScreens("login")
    data object ClientRegister : AuthScreens("client_register")
    data object SellerRegister : AuthScreens("seller_register")
}