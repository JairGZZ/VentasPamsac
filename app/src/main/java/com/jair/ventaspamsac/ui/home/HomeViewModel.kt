package com.jair.ventaspamsac.ui.home



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.domain.usecases.market.GetAllMarketsUseCase
import com.jair.ventaspamsac.domain.usecases.market.InsertMarketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertMarketUseCase: InsertMarketUseCase,
    private val getAllMarketsUseCase: GetAllMarketsUseCase,
//
    ) : ViewModel() {

    // LiveData para almacenar los markets del usuario
    private val _markets = MutableLiveData<List<MarketEntity>>(emptyList())
    val items: LiveData<List<MarketEntity>> get() = _markets
    fun loadMarkets() {
        viewModelScope.launch {
            val markets = getAllMarketsUseCase()
            _markets.value = markets
        }
    }

    fun insertMarket(marketItem: MarketEntity) {
        viewModelScope.launch {
            insertMarketUseCase(marketItem)
            loadMarkets()
        }
    }
}

//
//    val items = getAllMarketsUseCase()
//    fun updateMarket(market: MarketItem) {
//        viewModelScope.launch {
//            insertMarketUseCase(market)
//        }
//
//    }
//}