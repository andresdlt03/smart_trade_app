package com.example.smarttrade.product_management.model

class Food(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    category: String,
    val calories: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "Technology"
) {
}