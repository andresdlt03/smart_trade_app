package com.example.smarttrade.lists

import com.example.smarttrade.lists.viewmodel.state.CartItem

object CartState {
    private var _cart: MutableList<CartItem> = mutableListOf()

    fun getCart(): List<CartItem> {
        return _cart
    }

    fun addToCart(item: CartItem) {
        _cart.add(item)
    }

    fun removeFromCart(item: CartItem) {
        _cart.remove(item)
    }

    fun clearCart() {
        _cart.clear()
    }

    fun getCartSize(): Int {
        return _cart.size
    }

    fun getCartTotal(): Double {
        var total = 0.0
        for (item in _cart) {
            total += (item.price * item.quantity)
        }
        return total
    }
}