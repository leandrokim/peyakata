package com.example.peyakata.core.repository

import com.example.peyakata.core.model.BulkPrice

interface BulkPriceRepository {
    fun getPrices(): List<BulkPrice>
}