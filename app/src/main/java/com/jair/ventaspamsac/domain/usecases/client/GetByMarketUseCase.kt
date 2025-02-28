//package com.jair.ventaspamsac.domain.usecases.client
//
//import androidx.lifecycle.LiveData
//import com.jair.ventaspamsac.data.database.entities.ClientEntity
//import com.jair.ventaspamsac.data.database.repository.ClientRepository
//import com.jair.ventaspamsac.domain.items.ClientItem
//import javax.inject.Inject
//
//class GetByMarketUseCase @Inject constructor(private val clientRepository: ClientRepository) {
//
//    operator fun invoke (id: Int) : LiveData<List<ClientItem>> {
//        return clientRepository.getByMarket(id)
//    }
//}