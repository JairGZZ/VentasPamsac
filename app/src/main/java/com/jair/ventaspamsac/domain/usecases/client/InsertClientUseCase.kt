package com.jair.ventaspamsac.domain.usecases.client

import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.data.database.repository.ClientRepository
import javax.inject.Inject

class InsertClientUseCase @Inject constructor(private val clientRepository: ClientRepository) {

    suspend operator fun invoke(marketId: String, client: ClientEntity) {
        clientRepository.insertClient(marketId,client)
    }
}