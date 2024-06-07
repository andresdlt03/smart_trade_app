package com.example.smarttrade.product_management.domain.model

class Clothes(
    name: String,
    description: String,
    dataSheet: String,
    price: Double,
    photo: ByteArray,
    val size: String,
): Product(
    name,
    description,
    dataSheet,
    price,
    photo,
    "clothes"
) {
}