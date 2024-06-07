package com.example.smarttrade.catalogue.presentation.viewmodel.state

import com.example.smarttrade.product_management.domain.model.ProductAvailability

data class ProductDetailsState (
    val stockSelected: String = "0",
    val priceSelected: Double = 0.0,
    val availabilities: List<ProductAvailability> = emptyList(),
    val availabilitiesString: List<String> = emptyList()
)