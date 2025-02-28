package com.jair.ventaspamsac.domain.usecases.auth

import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): User? {
        return authRepository.getCurrentUser()
    }

}