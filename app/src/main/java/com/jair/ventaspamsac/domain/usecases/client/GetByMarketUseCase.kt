package com.jair.ventaspamsac.domain.usecases.client

import androidx.lifecycle.LiveData
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.data.database.repository.ClientRepository
import javax.inject.Inject

class GetByMarketUseCase @Inject constructor(private val clientRepository: ClientRepository) {

    suspend operator fun invoke (id: String) : List<ClientEntity>{
        return clientRepository.getByMarket(id)
    }
}