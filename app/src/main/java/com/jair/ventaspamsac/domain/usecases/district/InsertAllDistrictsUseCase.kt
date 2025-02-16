package com.jair.ventaspamsac.domain.usecases.district

import com.jair.ventaspamsac.data.database.repository.DistrictRepository
import com.jair.ventaspamsac.domain.items.DistrictItem
import javax.inject.Inject

class InsertAllDistrictsUseCase @Inject constructor(private val repository: DistrictRepository) {
    suspend operator fun invoke(districts: List<DistrictItem>) {
        repository.insertAllDistrict(districts)
    }

}