package com.example.smarttrade.catalogue.viewmodel

import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.catalogue.view.Catalogue

class clientCatalogueViewModel : Catalogue {
    override fun loadAndShowProducts(user: User) {
        // val approvedProducts = ProductService.getAllProductsByUser()

        /* Queda por implementar, aquí trae todos los productos de la base de
        *  todos los productos de ese vendedor en concreto
        * */
    }
    override fun removeProduct(user: User, product: Product) {
        // Los clientes no pueden borrar productos de la BD
    }
}