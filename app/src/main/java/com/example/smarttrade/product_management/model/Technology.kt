package com.example.smarttrade.product_management.model

class Technology(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    category: String,
    val consume: String,
    val model: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "Technology"
) {
}