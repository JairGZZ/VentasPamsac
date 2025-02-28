package com.jair.ventaspamsac.data.database.repository

import com.google.firebase.auth.FirebaseAuth
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.entities.toDomainUser
import com.jair.ventaspamsac.data.database.interfaces.IAuthRepository
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.data.database.repository.states.UserSession
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val userSession: UserSession
) : IAuthRepository {

    override suspend fun signIn(email: String, password: String): AuthResult<User> {
        return try {
            AuthResult.Loading
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user?.toDomainUser() ?: throw Exception("Usuario no encontrado")
            AuthResult.Success(user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun signUp(email: String, password: String): AuthResult<User> {
        return try {
            AuthResult.Loading
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user?.toDomainUser() ?: throw Exception("Registro fallido")
            AuthResult.Success(user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Error en registro")
        }
    }

    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            auth.signOut()
            userSession.clearSession()
            AuthResult.SuccessWithoutData // <-- Usa el nuevo caso
        } catch (e: Exception) {
            AuthResult.Error("Error al cerrar sesión")
        }
    }

    override fun getCurrentUser(): User? {
        return auth.currentUser?.toDomainUser()
    }


}
