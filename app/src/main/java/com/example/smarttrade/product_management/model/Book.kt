package com.example.smarttrade.product_management.model

class Book(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    category: String,
    val ISBN: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "Technology"
) {
}