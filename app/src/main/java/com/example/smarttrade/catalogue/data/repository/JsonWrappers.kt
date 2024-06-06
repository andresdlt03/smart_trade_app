package com.example.smarttrade.catalogue.data.repository

import com.example.smarttrade.product_management.domain.model.Product
import com.example.smarttrade.product_management.domain.model.ProductDTO
import com.example.smarttrade.product_management.domain.model.ProductFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class ProductJsonWrapper(
    val product: ProductDTO,
    val category: String
)

data class ProductWrapper(
    val product: Product,
    val category: String
)

fun parseProductJsonWrapper(json: String): ProductJsonWrapper {
    val gson = Gson()
    return gson.fromJson(json, ProductJsonWrapper::class.java)
}

fun convertToProductWrapper(wrapper: ProductJsonWrapper): ProductWrapper {
    val productFactory = ProductFactory()
    val product = productFactory.createProductFromDTO(wrapper.product, wrapper.category)
    return ProductWrapper(product, wrapper.category)
}

fun parseProductJsonWrappers(json: String): List<ProductJsonWrapper> {
    val gson = Gson()
    val listType = object : TypeToken<List<ProductJsonWrapper>>() {}.type
    return gson.fromJson(json, listType)
}

fun convertToProductWrappers(wrappers: List<ProductJsonWrapper>): List<ProductWrapper> {
    val productFactory = ProductFactory()
    return wrappers.map { wrapper ->
        val product = productFactory.createProductFromDTO(wrapper.product, wrapper.category)
        ProductWrapper(product, wrapper.category)
    }
}