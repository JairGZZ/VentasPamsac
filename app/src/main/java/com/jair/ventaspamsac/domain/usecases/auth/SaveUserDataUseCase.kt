package com.jair.ventaspamsac.domain.usecases.auth

import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.FirestoreRepository
import javax.inject.Inject

class SaveUserDataUseCase @Inject constructor(private val firestoreRepository: FirestoreRepository) {
    suspend operator fun invoke(user: User) = firestoreRepository.saveUserData(user)
}