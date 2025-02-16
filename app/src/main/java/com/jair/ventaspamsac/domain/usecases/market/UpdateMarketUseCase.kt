package com.jair.ventaspamsac.domain.usecases.market

import com.jair.ventaspamsac.data.database.repository.MarketRepository
import com.jair.ventaspamsac.domain.items.MarketItem
import javax.inject.Inject

class UpdateMarketUseCase @Inject constructor(private val repository: MarketRepository) {
    suspend operator fun invoke(market: MarketItem) {
        repository.updateMarket(market)
    }

}