package com.example.smarttrade.product_management.presentation.viewmodel.state

data class ProductTechnologyState (
    var model: String = "",
    var modelError: String? = null,
    var energy: String = "",
    var energyError: String? = null,
)