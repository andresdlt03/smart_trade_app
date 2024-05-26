package com.example.smarttrade.product_management.presentation.viewmodel.state

import android.net.Uri

data class ProductState (
    var name: String = "",
    var nameError: String? = null,
    var description: String = "",
    var descriptionError: String? = null,
    var dataSheet: String = "",
    var dataSheetError: String? = null,
    var price: String = "",
    var priceError: String? = null,
    var stock: String = "",
    var stockError: String? = null,
    var photo1: Uri? = null,
    var photo2: Uri? = null,
)