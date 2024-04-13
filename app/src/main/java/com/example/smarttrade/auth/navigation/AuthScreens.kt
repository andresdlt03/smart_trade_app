package com.example.smarttrade.auth.navigation

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens("login")
    object ClientRegister : AuthScreens("client_register")
    object SellerRegister : AuthScreens("seller_register")
}