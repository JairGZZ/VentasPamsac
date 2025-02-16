package com.jair.ventaspamsac.domain.usecases.district

import androidx.lifecycle.LiveData
import com.jair.ventaspamsac.data.database.repository.DistrictRepository
import com.jair.ventaspamsac.domain.items.DistrictItem
import javax.inject.Inject

class GetAllDistrictsUseCase @Inject constructor(private val districtRepository: DistrictRepository) {
     operator fun invoke(): LiveData<List<DistrictItem>> {
        return districtRepository.getAllDistricts()
    }

}