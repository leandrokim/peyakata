package com.example.peyakata.core.action

import com.example.peyakata.core.model.Cart
import org.junit.Test

class CreateNewCartShould {
    lateinit var action: CreateNewCart

    @Test
    fun `create a cart when requested`() {
        action = givenAnAction()

        val cart = whenCreatingNewCart()

        thenEmptyCartIsReturned(cart)
    }

    private fun givenAnAction(): CreateNewCart {
        return CreateNewCart()
    }

    private fun whenCreatingNewCart(): Cart {
        return action()
    }

    private fun thenEmptyCartIsReturned(cart: Cart) {
        assert(emptyCart == cart)
    }


    private companion object {
        val emptyCart = Cart("id", emptyList())
    }
}