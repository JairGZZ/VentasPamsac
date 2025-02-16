package com.jair.ventaspamsac.domain.usecases.district

import com.jair.ventaspamsac.data.database.repository.DistrictRepository
import javax.inject.Inject

class DeleteAllDistrictUseCase @Inject constructor(private val repository: DistrictRepository) {
    suspend operator fun invoke (){
        repository.deleteAll()

    }
}