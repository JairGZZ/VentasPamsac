package com.jair.ventaspamsac.domain.usecases.market

import androidx.lifecycle.LiveData
import com.jair.ventaspamsac.data.database.repository.MarketRepository
import com.jair.ventaspamsac.domain.items.MarketItem
import javax.inject.Inject

class GetAllMarketsUseCase @Inject constructor(private val repository: MarketRepository) {

    operator fun invoke(): LiveData<List<MarketItem>>{
        return repository.getAllMarkets()
    }
}