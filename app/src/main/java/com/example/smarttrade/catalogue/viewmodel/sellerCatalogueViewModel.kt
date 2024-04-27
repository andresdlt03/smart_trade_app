package com.example.smarttrade.catalogue.viewmodel

import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.catalogue.view.Catalogue

class sellerCatalogueViewModel : Catalogue {
    override fun loadAndShowProducts(user: User) {
        // val approvedProducts = ProductService.getAllProductsByUser()

        /* Queda por implementar, aquí trae todos los productos de la base de
        *  todos los productos de ese vendedor en concreto
        * */
    }
    override fun removeProduct(user: User, product: Product) {
        // ProductService.removeProductByUser(user, product)
    }
}