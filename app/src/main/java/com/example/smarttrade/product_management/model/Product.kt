package com.example.smarttrade.product_management.model;

abstract class Product(
    val name: String,
    val description: String,
    val dataSheet: String,
    val photos: List<String>,
    val category: String,
) {
}