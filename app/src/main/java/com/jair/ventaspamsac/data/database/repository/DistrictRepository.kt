package com.jair.ventaspamsac.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jair.ventaspamsac.data.database.dao.DistrictDAO
import com.jair.ventaspamsac.domain.items.DistrictItem
import com.jair.ventaspamsac.domain.items.toDistrictEntity
import com.jair.ventaspamsac.domain.items.toDistrictItem
import javax.inject.Inject

class DistrictRepository @Inject constructor(private val districtDAO: DistrictDAO){
    suspend fun insertAllDistrict(districts: List<DistrictItem>) {
        districtDAO.insertAll( districts.map { it.toDistrictEntity() })
    }
    suspend fun deleteAll(){
        districtDAO.deleteAllDistricts()
    }
    fun getAllDistricts(): LiveData<List<DistrictItem>> {
        return districtDAO.getAll().map { entities ->
            entities.map { entity -> entity.toDistrictItem() }
        }
    }
}