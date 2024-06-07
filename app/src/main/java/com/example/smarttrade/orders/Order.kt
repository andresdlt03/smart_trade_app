package com.example.smarttrade.orders

data class Order (
    val items: MutableList<String> = mutableListOf(),
    val orderNumber: String = "",
    val orderDate: String = "",
    val totalAmount: Double = 0.0,
    val paymentMethod: String = ""
)