package com.example.smarttrade.product_management.presentation.viewmodel.state

import android.net.Uri

data class ProductTechnologyState (
    var name: String = "",
    var description: String = "",
    var model: String = "",
    var dataSheet: String = "",
    var energy: String = "",
    var price: String = "",
    var stock: String = "",
    var photo1: Uri? = null,
    var photo2: Uri? = null,
    var checkVariables: Boolean = false,
    var textError: String = ""
) {
}