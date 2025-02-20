package com.jair.ventaspamsac.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.domain.items.MarketItem
import com.jair.ventaspamsac.domain.usecases.market.GetAllMarketsUseCase
import com.jair.ventaspamsac.domain.usecases.market.InsertMarketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertMarketUseCase: InsertMarketUseCase,
    private val getAllMarketsUseCase: GetAllMarketsUseCase,

    ) : ViewModel() {

     fun insertMarket(marketItem: MarketItem){
         viewModelScope.launch {
        insertMarketUseCase(marketItem)
         }
    }

    val items = getAllMarketsUseCase()
    fun updateMarket(market: MarketItem) {
        viewModelScope.launch {
            insertMarketUseCase(market)
        }

    }
}