//package com.jair.ventaspamsac.data.database.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.map
//
//
//import javax.inject.Inject
//
//class ClientRepository @Inject constructor(private val clientDAO: ClientDAO)  {
//
//    fun getByMarket(id: Int) : LiveData<List<ClientItem>> {
//        return clientDAO.getByMarket(id).map { entities -> entities.map { entity -> entity.toClientItem() } }
//    }
//    suspend fun insert(clientItem: ClientItem) {
//        clientDAO.insert(clientItem.toClientEntity())
//    }
//    suspend fun update(clientItem: ClientItem) {
//        clientDAO.update(clientItem.toClientEntity())
//    }
//
//}