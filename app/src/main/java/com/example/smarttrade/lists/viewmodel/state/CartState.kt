package com.example.smarttrade.lists.viewmodel.state

data class CartState (
    var cart: MutableList<CartItem> = mutableListOf(),
    var cartSize: Int = 0,
    var cartTotal: Double = 0.0
)