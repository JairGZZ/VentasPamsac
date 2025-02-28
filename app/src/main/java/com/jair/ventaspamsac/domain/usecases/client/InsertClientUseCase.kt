//package com.jair.ventaspamsac.domain.usecases.client
//
//import com.jair.ventaspamsac.data.database.repository.ClientRepository
//import com.jair.ventaspamsac.domain.items.ClientItem
//import javax.inject.Inject
//
//class InsertClientUseCase @Inject constructor(private val clientRepository: ClientRepository) {
//
//    suspend operator fun invoke(client: ClientItem) {
//        clientRepository.insert(client)
//    }
//}