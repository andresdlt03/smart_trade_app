package com.example.smarttrade.catalogue.viewmodel


import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.catalogue.view.Catalogue

class adminCatalogueViewModel : Catalogue {
    override fun loadAndShowProducts(user: User) {
        // val approvedProducts = ProductService.getAllApprovedProducts()
        // val notApprovedProducts = ProductService.getAllNotApprovedProducts()

        /* Queda por implementar, aquí trae todos los productos de la base de
        * metiendo a todos los productos que se han aprobado en una lista y
        * aquellos que no se han aprobado AÚN en otra lista
        *
        * SE MOSTRARÁN EN SECCIONES DISTINTAS
        * */
    }
    override fun removeProduct(user: User, product: Product) {
        // user = product.getSeller
        // ProductService.removeProductByUser(user, product)
    }

}