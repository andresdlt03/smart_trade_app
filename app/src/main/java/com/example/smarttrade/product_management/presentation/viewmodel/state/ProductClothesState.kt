package com.example.smarttrade.product_management.presentation.viewmodel.state

import android.net.Uri

data class ProductClothesState (
    var name: String = "",
    var description: String = "",
    var size: String = "",
    var price: String = "",
    var stock: String = "",
    var photo1: Uri? = null,
    var photo2: Uri? = null,
    var checkVariables: Boolean = false,
    var textError: String = ""
)