package com.jair.ventaspamsac.domain.usecases.auth

import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.AuthRepository
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import javax.inject.Inject


class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveUserDataUseCase: SaveUserDataUseCase
) {
    suspend operator fun invoke(name: String, email: String, password: String): AuthResult<Unit> {
        return try {
            // 1. Registrar en Authentication
            when (val authResult = authRepository.signUp(email, password)) {
                is AuthResult.Success -> {
                    // 2. Crear User con datos combinados
                    val firebaseUser = authResult.data
                    val userWithName = User(
                        id = firebaseUser.id,
                        name = name,
                        email = firebaseUser.email,
                        isVerified = firebaseUser.isVerified
                    )

                    // 3. Guardar en Firestore
                    val saveResult = saveUserDataUseCase(userWithName)
                     // Puedes manejar errores aquí si es necesario

                    AuthResult.Success(Unit)
                }
                is AuthResult.Error -> authResult
                else -> AuthResult.Error("Flujo inesperado")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Error en registro")
        }
    }
}