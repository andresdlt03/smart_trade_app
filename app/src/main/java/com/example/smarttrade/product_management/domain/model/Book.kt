package com.example.smarttrade.product_management.domain.model

class Book(
    name: String,
    description: String,
    dataSheet: String,
    price: Double,
    photo: ByteArray?,
    val ISBN: String,
): Product(
    name,
    description,
    dataSheet,
    price,
    photo,
    "book"
) {
}