package com.example.peyakata.core.action

import com.example.peyakata.core.model.Cart
import com.example.peyakata.core.model.Item

class AddItemToCart {

    operator fun invoke(item: Item, cart: Cart): Cart {
        val items = cart.items.toMutableList()
        cart.items.find { itemInCart -> itemInCart.name == item.name }?.let { itemInCart ->
            itemInCart.amount++
        } ?: run {
            items.add(item.toCartItem())
        }

        return Cart(cart.id, items)
    }

}