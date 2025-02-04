package com.example.peyakata.core.model

data class Cart(val id: String, val items: List<ItemInCart>) {

    fun calculateTotal() = items.sumOf { item -> item.price }
}