package com.example.peyakata.core.action

import com.example.peyakata.core.model.Cart

class CreateNewCart {
    operator fun invoke(): Cart {
        return Cart("id", emptyList())
    }
}