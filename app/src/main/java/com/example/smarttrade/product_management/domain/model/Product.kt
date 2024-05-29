package com.example.smarttrade.product_management.domain.model;

abstract class Product(
    val name: String,
    val description: String,
    val dataSheet: String,
    val price: Double,
    val photo: ByteArray?,
    val category: String,
) {
}