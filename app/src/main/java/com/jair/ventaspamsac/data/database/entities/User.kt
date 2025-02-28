package com.jair.ventaspamsac.data.database.entities

import com.google.firebase.auth.FirebaseUser

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val isVerified: Boolean = false
)
fun FirebaseUser.toDomainUser(): User {
    return User(
        id = uid,
        email = email ?: "",
        name = displayName ?: "",
        isVerified = isEmailVerified
    )
}