package com.jair.ventaspamsac.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jair.ventaspamsac.data.database.entities.DistrictEntity

@Dao
interface DistrictDAO {
    @Query("SELECT * FROM districts ")
    fun getAll(): LiveData<List<DistrictEntity>>

    @Query("SELECT * FROM districts WHERE id_district = :id")
    suspend fun getById( id: Int): DistrictEntity

    @Query("SELECT * FROM districts WHERE name LIKE :name")
    suspend fun getByNameLike(name: String): List<DistrictEntity>

    @Insert
    suspend fun insert(district: DistrictEntity)

    @Insert
    suspend fun insertAll(districts: List<DistrictEntity>)

    @Delete
    suspend fun delete(district: DistrictEntity)

    @Query("DELETE FROM districts")
    suspend fun deleteAllDistricts()
}
