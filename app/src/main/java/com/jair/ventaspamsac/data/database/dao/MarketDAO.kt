package com.jair.ventaspamsac.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jair.ventaspamsac.data.database.entities.MarketEntity

@Dao
interface MarketDAO {
    @Query("SELECT * FROM markets")
    fun getAll(): LiveData<List<MarketEntity>>

    @Query("SELECT * FROM markets WHERE id_market = :id")
    suspend fun getById(id: Int): MarketEntity

    @Query("SELECT * FROM markets WHERE name LIKE :name")
    suspend fun getByNameLike(name: String): List<MarketEntity>

    @Insert
    suspend fun insert(market: MarketEntity)

    @Delete
    suspend fun delete(market: MarketEntity)

    @Query("SELECT * FROM markets WHERE district = :district")
    suspend fun getByDistrict(district: Int): List<MarketEntity>

    @Update
    suspend fun updateMarket(market: MarketEntity)



}