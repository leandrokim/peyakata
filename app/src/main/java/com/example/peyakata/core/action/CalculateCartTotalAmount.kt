package com.example.peyakata.core.action

import com.example.peyakata.core.model.BulkPrice
import com.example.peyakata.core.model.Cart
import com.example.peyakata.core.model.ItemInCart
import com.example.peyakata.core.repository.BulkPriceRepository

class CalculateCartTotalAmount(private val repository: BulkPriceRepository) {

    operator fun invoke(cart: Cart): Double {
        val bulkPrices = repository.getPrices().associateBy { it.name }
        var totalPrice = 0.0

        cart.items.forEach { item ->
            val bulkPrice = bulkPrices[item.name]

            totalPrice += if (isValidBulkPriceForItem(bulkPrice, item)) {
                val bulkSetCount = item.amount / bulkPrice!!.amount
                val remainingItems = item.amount % bulkPrice.amount

                (bulkSetCount * bulkPrice.price) + (remainingItems * item.price)
            } else {
                item.amount * item.price
            }
        }

        return totalPrice
    }

    private fun isValidBulkPriceForItem(
        bulkPrice: BulkPrice?,
        item: ItemInCart
    ) = bulkPrice != null && item.amount >= bulkPrice.amount

}