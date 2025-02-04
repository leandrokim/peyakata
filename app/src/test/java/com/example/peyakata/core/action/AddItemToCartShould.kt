package com.example.peyakata.core.action

import com.example.peyakata.core.model.Cart
import com.example.peyakata.core.model.Item
import com.example.peyakata.core.model.ItemInCart
import org.junit.Test


class AddItemToCartShould {
    lateinit var action: AddItemToCart

    @Test
    fun `if adding item to empty cart, now has 1 item`() {
        givenAnAction()

        val newCart = whenAddingAnItemToCart(action, item2, emptyCart)

        thenCartHasNoOfItems(newCart, 1)
        assert(newCart.items[0].amount == 1)
    }

    @Test
    fun `if adding item to cart with 1 item, now has 2 items`() {
        val filledCart = givenACartWithOneItem()
        givenAnAction()

        val newCart = whenAddingAnItemToCart(action, item2, filledCart)

        thenCartHasNoOfItems(newCart, 2)
        assert(newCart.items[0].amount == 1)
        assert(newCart.items[1].amount == 1)
    }

    @Test
    fun `if adding two same items, it has only one item in cart with amount two`() {
        val filledCart = givenACartWithOneItem()
        givenAnAction()

        val newCart = whenAddingAnItemToCart(action, item, filledCart)

        thenCartHasNoOfItems(newCart, 1)
        assert(newCart.items[0].amount == 2)
    }

    private fun givenACartWithOneItem(): Cart {
        return Cart("id", listOf(cartItem))
    }

    private fun givenAnAction() {
        action = AddItemToCart()
    }

    private fun whenAddingAnItemToCart(
        action: AddItemToCart,
        item: Item,
        cart: Cart
    ): Cart {
        return action(item, cart)
    }

    private fun thenCartHasNoOfItems(newCart: Cart, itemsSize: Int) {
        assert(newCart.items.size == itemsSize)
    }


    private companion object {
        val emptyCart = Cart("id", emptyList())
        val item = Item("Fideos", 10.0)
        val item2 = Item("Jugo", 15.0)
        val cartItem = ItemInCart("Fideos", 1, 10.0)
        val cartItem2 = ItemInCart("Jugo", 1, 15.0)
    }
}