package com.example.peyakata.core.action

import com.example.peyakata.core.model.BulkPrice
import com.example.peyakata.core.model.Cart
import com.example.peyakata.core.model.ItemInCart
import com.example.peyakata.core.repository.BulkPriceRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class CalculateCartTotalAmountShould {
    private val repository: BulkPriceRepository = mockk(relaxed = true)
    private lateinit var action: CalculateCartTotalAmount

    @Test
    fun `if cart is empty price is nil`() {
        val cart = givenAnEmptyCart()
        action = givenAnAction()

        val totalPrice = whenAskingCartTotalPrice(action, cart)

        thenPriceIs(totalPrice, emptyCartPrice)
    }

    @Test
    fun `if cart has two items returns calculated sum`() {
        val cart = givenACartWithItems(listOf(cartItem, cartItem2))
        action = givenAnAction()

        val totalPrice = whenAskingCartTotalPrice(action, cart)

        thenPriceIs(totalPrice, twoItemsTotalPrice)
    }

    @Test
    fun `with bulk price, two pears should calculate $18`() {
        val cart = givenACartWithItems(listOf(cartItemDouble))
        every { repository.getPrices() } returns listOf(pearBulkPrice)
        action = givenAnAction()

        val totalPrice = whenAskingCartTotalPrice(action, cart)

        thenPriceIs(totalPrice, twoPearBulkPrice)
    }

    private fun givenACartWithItems(items: List<ItemInCart>): Cart {
        return Cart("id", items)
    }

    private fun givenAnEmptyCart() = Cart("id", emptyList())

    private fun givenAnAction(): CalculateCartTotalAmount {
        return CalculateCartTotalAmount(repository)
    }

    private fun whenAskingCartTotalPrice(
        action: CalculateCartTotalAmount,
        cart: Cart
    ): Double {
        return action(cart)
    }

    private fun thenPriceIs(totalPrice: Double, expectedPrice: Double) {
        assert(totalPrice == expectedPrice)
    }

    private companion object {
        const val emptyCartPrice = 0.0
        val cartItem = ItemInCart("Pear", 1, 10.0)
        val cartItem2 = ItemInCart("Apple", 1, 20.0)
        val cartItemDouble = ItemInCart("Pear", 2, 10.0)
        val twoItemsTotalPrice = 30.0
        val twoPearBulkPrice = 18.0
        val pearBulkPrice = BulkPrice(name = "Pear", 2, 18.0)
    }
}