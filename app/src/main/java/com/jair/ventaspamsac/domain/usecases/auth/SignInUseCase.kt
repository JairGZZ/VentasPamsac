package com.jair.ventaspamsac.domain.usecases.auth

import com.jair.ventaspamsac.data.database.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor( private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = authRepository.signIn(email, password)
}