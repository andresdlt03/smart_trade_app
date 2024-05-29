package com.example.smarttrade.product_management.domain.model

class Food(
    name: String,
    description: String,
    dataSheet: String,
    price: Double,
    photo: ByteArray,
    val calories: String,
): Product(
    name,
    description,
    dataSheet,
    price,
    photo,
    "food"
) {
}