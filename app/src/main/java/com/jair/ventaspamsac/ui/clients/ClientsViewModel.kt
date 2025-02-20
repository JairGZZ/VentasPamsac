package com.jair.ventaspamsac.ui.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.domain.usecases.client.GetByMarketUseCase
import com.jair.ventaspamsac.domain.usecases.client.InsertClientUseCase
import com.jair.ventaspamsac.domain.usecases.client.UpdateClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor( private val GetByMarketUseCase: GetByMarketUseCase
, private val InsertClientUseCase: InsertClientUseCase
    , private val updateClientUseCase: UpdateClientUseCase
): ViewModel() {
    fun getByMarket(id: Int) = GetByMarketUseCase(id)
    suspend fun insert(client: ClientItem) = InsertClientUseCase(client)

    suspend fun up(client: ClientItem) {

            updateClientUseCase(client)


    }


}