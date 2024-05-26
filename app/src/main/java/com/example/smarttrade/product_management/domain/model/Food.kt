package com.example.smarttrade.product_management.domain.model

class Food(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    val calories: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "food"
) {
}