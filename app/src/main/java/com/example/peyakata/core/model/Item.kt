package com.example.peyakata.core.model

data class Item(val name: String, val price: Double) {
    fun toCartItem(): ItemInCart {
        return ItemInCart(name, 1, price)
    }
}