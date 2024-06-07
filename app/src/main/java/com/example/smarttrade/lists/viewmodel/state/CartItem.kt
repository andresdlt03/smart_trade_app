package com.example.smarttrade.lists.viewmodel.state

data class CartItem (
    var productName: String = "",
    var price: Double = 0.0,
    var quantity: Int = 0
)