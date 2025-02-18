package com.jair.ventaspamsac.ui.clients

import androidx.lifecycle.ViewModel
import com.jair.ventaspamsac.domain.usecases.client.GetByMarketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor( private val GetByMarketUseCase: GetByMarketUseCase): ViewModel() {
    fun getByMarket(id: Int) = GetByMarketUseCase(id)

}