package com.example.smarttrade.catalogue.view

import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.catalogue.viewmodel.Product

interface Catalogue {
    fun loadAndShowProducts(user: User)
    fun removeProduct(user: User, product: Product)
}