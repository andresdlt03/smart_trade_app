package com.example.smarttrade.product_management.domain.model

class Clothes(
    name: String,
    description: String,
    dataSheet: String,
    photo: ByteArray,
    val size: String,
): Product(
    name,
    description,
    dataSheet,
    photo,
    "clothes"
) {
}