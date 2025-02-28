package com.jair.ventaspamsac.domain.usecases.market

import com.jair.ventaspamsac.data.database.entities.MarketEntity

import com.jair.ventaspamsac.data.database.repository.MarketRepository
import javax.inject.Inject


class InsertMarketUseCase @Inject constructor(private val repository: MarketRepository) {
    suspend operator fun invoke(market: MarketEntity) {
        repository.insertMarket(market)
    }
}