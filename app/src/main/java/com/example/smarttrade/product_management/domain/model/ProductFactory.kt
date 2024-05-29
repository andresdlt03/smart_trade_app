package com.example.smarttrade.product_management.domain.model

import android.util.Base64

class ProductFactory {
    fun createProductFromDTO(productDTO: ProductDTO, category: String): Product {
        return when (category) {
            "technology" -> Technology(
                productDTO.name,
                productDTO.description,
                productDTO.dataSheet,
                productDTO.price.toDouble(),
                Base64.decode(productDTO.photo, Base64.DEFAULT),
                productDTO.model,
                productDTO.consume
            )
            "book" -> Book(
                productDTO.name,
                productDTO.description,
                productDTO.dataSheet,
                productDTO.price.toDouble(),
                Base64.decode(productDTO.photo, Base64.DEFAULT),
                productDTO.isbn
            )
            "clothes" -> Clothes(
                productDTO.name,
                productDTO.description,
                productDTO.dataSheet,
                productDTO.price.toDouble(),
                Base64.decode(productDTO.photo, Base64.DEFAULT),
                productDTO.size
            )
            "food" -> Food(
                productDTO.name,
                productDTO.description,
                productDTO.dataSheet,
                productDTO.price.toDouble(),
                Base64.decode(productDTO.photo, Base64.DEFAULT),
                productDTO.calories
            )
            else -> throw IllegalArgumentException("Categoría de producto desconocida: ${productDTO.category}")
        }
    }
}