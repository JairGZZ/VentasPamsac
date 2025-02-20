package com.jair.ventaspamsac.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jair.ventaspamsac.data.database.entities.ClientEntity

@Dao
interface ClientDAO {

    @Query("SELECT * FROM  clients WHERE  id_market = :id")
     fun getByMarket(id: Int): LiveData<List<ClientEntity>>

     @Insert
     suspend fun insert(clientEntity: ClientEntity)

     @Update
     suspend fun update(clientEntity: ClientEntity)
}