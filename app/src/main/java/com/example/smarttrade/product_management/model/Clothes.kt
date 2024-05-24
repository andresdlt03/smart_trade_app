package com.example.smarttrade.product_management.model

class Clothes(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    category: String,
    val size: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "Technology"
) {
}