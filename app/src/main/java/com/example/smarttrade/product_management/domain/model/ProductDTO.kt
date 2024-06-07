package com.example.smarttrade.product_management.domain.model

data class ProductDTO(
    val name: String,
    val description: String,
    val dataSheet: String,
    val price: String,
    val photo: String,
    val category: String,

    // technology
    val model: String,
    val consume: String,

    // books
    val isbn: String,

    // clothes
    val size: String,

    // food
    val calories: String
)