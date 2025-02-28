package com.jair.ventaspamsac.ui.clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.domain.usecases.client.GetByMarketUseCase
//import com.jair.ventaspamsac.domain.items.ClientItem
//import com.jair.ventaspamsac.domain.usecases.client.GetByMarketUseCase
import com.jair.ventaspamsac.domain.usecases.client.InsertClientUseCase
//import com.jair.ventaspamsac.domain.usecases.client.UpdateClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
private val InsertClientUseCase: InsertClientUseCase
//    , private val updateClientUseCase: UpdateClientUseCase
    , private val getByMarketUseCase: GetByMarketUseCase
): ViewModel() {
    private val _clients = MutableLiveData<List<ClientEntity>>(emptyList())
    val items: LiveData<List<ClientEntity>> get() = _clients

    suspend fun insert(marketId: String,client: ClientEntity){
        InsertClientUseCase(marketId,client)
        loadClients(marketId)
    }
     fun loadClients(marketId: String) {

        viewModelScope.launch {
            val clients = getByMarketUseCase(marketId)
            _clients.value = clients
        }
    }

//    suspend fun up(client: ClientItem) {
//            updateClientUseCase(client)
//    }


}