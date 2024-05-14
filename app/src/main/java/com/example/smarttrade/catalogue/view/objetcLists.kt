package com.example.smarttrade.catalogue.view

import com.example.smarttrade.catalogue.viewmodel.Product

class objetcLists {
    object ListaCarrito {
        private val items = mutableListOf<Product>()


        fun addItem(item: Product) {
            items.add(item)
        }

        fun removeItem(item: Product) {
            items.remove(item)
        }

        fun clearItems() {
            items.clear()
        }

        fun containsItem(item: Product): Boolean {
            return items.contains(item)
        }

        fun getItems(): List<Product> {
            return items.toList()
        }

    }

    object ListaGuardarTarde {
        private val items = mutableListOf<Product>()

        fun addItem(item: Product) {
            items.add(item)
        }

        fun removeItem(item: Product) {
            items.remove(item)
        }

        fun getItems(): List<Product> {
            return items.toList()
        }
    }

    object ListaDeseados {
        private val items = mutableListOf<Product>()

        fun addItem(item: Product) {
            items.add(item)
        }

        fun removeItem(item: Product) {
            items.remove(item)
        }

        fun getItems(): List<Product> {
            return items.toList()
        }
        fun containsItem(item: Product): Boolean {
            return items.contains(item)
        }
    }


}