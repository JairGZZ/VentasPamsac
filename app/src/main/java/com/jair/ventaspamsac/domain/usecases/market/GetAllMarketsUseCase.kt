package com.jair.ventaspamsac.domain.usecases.market

import androidx.lifecycle.LiveData
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.data.database.repository.MarketRepository
import javax.inject.Inject

class GetAllMarketsUseCase @Inject constructor(private val repository: MarketRepository) {

    suspend operator fun invoke():List<MarketEntity>{
        return repository.getAllMarkets()
    }
}