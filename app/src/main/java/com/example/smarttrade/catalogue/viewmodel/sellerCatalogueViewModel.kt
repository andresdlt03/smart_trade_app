package com.example.smarttrade.catalogue.viewmodel

class sellerCatalogueViewModel : catalogueViewModel() {

        fun addToCatalogue(Producto: Product){
            listaCatalogo.add(Producto)
            _filteredProduct.value = listaCatalogo
        }
        // val approvedProducts = ProductService.getAllProductsByUser()

        /* Queda por implementar, aquí trae todos los productos de la base de
        *  todos los productos de ese vendedor en concreto
        * */

        // ProductService.removeProductByUser(user, product)

}