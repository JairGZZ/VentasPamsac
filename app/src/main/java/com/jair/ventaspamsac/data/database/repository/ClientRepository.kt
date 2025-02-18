package com.jair.ventaspamsac.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jair.ventaspamsac.data.database.dao.ClientDAO
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.domain.items.ClientItem
import com.jair.ventaspamsac.domain.items.toClientItem
import javax.inject.Inject

class ClientRepository @Inject constructor(private val clientDAO: ClientDAO)  {

    fun getByMarket(id: Int) : LiveData<List<ClientItem>> {
        return clientDAO.getByMarket(id).map { entities -> entities.map { entity -> entity.toClientItem() } }
    }
}