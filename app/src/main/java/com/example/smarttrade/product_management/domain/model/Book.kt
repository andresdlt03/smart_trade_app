package com.example.smarttrade.product_management.domain.model

class Book(
    name: String,
    description: String,
    dataSheet: String,
    photos: List<String>,
    val ISBN: String,
): Product(
    name,
    description,
    dataSheet,
    photos,
    "book"
) {
}