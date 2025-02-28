package com.jair.ventaspamsac.data.database.interfaces

import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.states.AuthResult

interface IAuthRepository {
    suspend fun signIn(email: String, password: String): AuthResult<User>
    suspend fun signUp(email: String, password: String): AuthResult<User>
    suspend fun signOut(): AuthResult<Unit>
    fun getCurrentUser(): User?
}