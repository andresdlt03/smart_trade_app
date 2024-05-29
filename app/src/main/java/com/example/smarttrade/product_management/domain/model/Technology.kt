package com.example.smarttrade.product_management.domain.model

class Technology(
    name: String,
    description: String,
    dataSheet: String,
    price: Double,
    photo: ByteArray,
    val consume: String,
    val model: String,
): Product(
    name,
    description,
    dataSheet,
    price,
    photo,
    "technology"
) {
}