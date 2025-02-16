package com.jair.ventaspamsac.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jair.ventaspamsac.data.database.dao.MarketDAO
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.domain.items.MarketItem
import com.jair.ventaspamsac.domain.items.toMarketEntity
import com.jair.ventaspamsac.domain.items.toMarketItem
import javax.inject.Inject

class MarketRepository @Inject constructor(private val marketDAO: MarketDAO) {
    suspend fun insertMarket(market: MarketItem) {
        marketDAO.insert(market.toMarketEntity())
    }
    fun getAllMarkets(): LiveData<List<MarketItem>> {
        return marketDAO.getAll().map { entities ->
            entities.map { entity -> entity.toMarketItem() }
        }

    }
    suspend fun updateMarket(market: MarketItem) {
        marketDAO.updateMarket(market.toMarketEntity())
    }

}