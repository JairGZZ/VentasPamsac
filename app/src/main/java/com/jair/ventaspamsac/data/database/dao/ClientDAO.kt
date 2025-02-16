package com.jair.ventaspamsac.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jair.ventaspamsac.data.database.entities.ClientEntity

@Dao
interface ClientDAO {

    @Query("SELECT * FROM  clients WHERE  id_market = :id")
    suspend fun getByMarket(id: Int): ClientEntity
}