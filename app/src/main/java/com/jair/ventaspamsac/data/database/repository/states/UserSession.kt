package com.jair.ventaspamsac.data.database.repository.states

import androidx.lifecycle.LifecycleOwner
import com.google.firebase.auth.FirebaseAuth
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.entities.toDomainUser
import javax.inject.Inject

class UserSession @Inject constructor(
    private val auth: FirebaseAuth
) {
    val currentUserId: String?
        get() = auth.currentUser?.uid

    fun clearSession() {
        auth.signOut()
    }

    // Observador de estado de autenticación
    fun observeAuthState(owner: LifecycleOwner, callback: (User?) -> Unit) {
        auth.addAuthStateListener { firebaseAuth ->
            callback(firebaseAuth.currentUser?.toDomainUser())
        }
    }
}