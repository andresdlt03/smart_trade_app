package com.example.smarttrade.product_management.domain.model

class Technology(
    name: String,
    description: String,
    dataSheet: String,
    photo: ByteArray,
    val consume: String,
    val model: String,
): Product(
    name,
    description,
    dataSheet,
    photo,
    "technology"
) {
}