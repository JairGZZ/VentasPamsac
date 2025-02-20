package com.jair.ventaspamsac.ui.clients

import androidx.lifecycle.ViewModel
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.domain.usecases.client.GetByMarketUseCase
import com.jair.ventaspamsac.domain.usecases.client.InsertClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor( private val GetByMarketUseCase: GetByMarketUseCase
, private val InsertClientUseCase: InsertClientUseCase
): ViewModel() {
    fun getByMarket(id: Int) = GetByMarketUseCase(id)
    suspend fun insert(client: ClientItem) = InsertClientUseCase(client)


}