package com.example.smarttrade.product_management.domain.model

class Clothes(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    val size: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "clothes"
) {
}