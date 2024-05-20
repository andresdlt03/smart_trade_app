package com.example.smarttrade.catalogue.view

import com.example.smarttrade.catalogue.viewmodel.Product

class objetcLists {
    object shoppingCart {
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

    object forLaterList {
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

    object wishList {
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