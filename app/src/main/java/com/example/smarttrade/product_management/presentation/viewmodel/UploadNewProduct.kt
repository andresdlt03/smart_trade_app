package com.example.smarttrade.product_management.presentation.viewmodel

import com.example.smarttrade.product_management.domain.model.Product
import com.example.smarttrade.product_management.domain.repository.ProductRepository

suspend fun uploadNewProduct(
    product: Product,
    price: Double,
    stock: Int,
    sellerEmail: String,
    productRepository: ProductRepository,
    setSuccess: (Boolean) -> Unit,
    setUploadError: (String) -> Unit
) {
    try {
        val call = productRepository.createProduct(
            product,
            price,
            stock,
            sellerEmail
        )
        if(call.isSuccessful) {
            setSuccess(true)
        } else {
            val error = call.errorBody()?.string()
            setUploadError(error.toString())
        }
    } catch (e: Exception) {
        setUploadError(e.message.toString())
    }
}