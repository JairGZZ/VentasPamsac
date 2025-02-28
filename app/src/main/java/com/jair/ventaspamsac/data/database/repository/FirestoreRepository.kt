package com.jair.ventaspamsac.data.database.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.data.database.entities.User
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.data.database.repository.states.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {


    suspend fun saveUserData(user: User): AuthResult<Unit> {
        return try {
            firestore.collection("users")
                .document(user.id)
                .set(user.toMap())
                .await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Error guardando datos")
        }
    }
    private fun User.toMap(): Map<String, Any> = hashMapOf(
        "email" to email,
        "name" to name,
        "isVerified" to isVerified
    )


    // Ejemplo: Obtener todos los markets del usuario
//    suspend fun getMarkets(): Result<List<MarketEntity>> = withContext(Dispatchers.IO) {
//        try {
//            val snapshot = firestore.collection("users")
//                .document(currentUserId)
//                .collection("markets")
//                .get()
//                .await()
//
//            val markets = snapshot.documents.map { doc ->
//                MarketEntity(
//                    id = doc.id,
//                    name = doc.getString("name") ?: "",
//                    district = doc.getString("district") ?: ""
//                )
//            }
//            Result.success(markets)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    // Operación para agregar un market
//    suspend fun addMarket(market: MarketEntity): Result<String> = withContext(Dispatchers.IO) {
//        try {
//            val documentRef = firestore.collection("users")
//                .document(currentUserId)
//                .collection("markets")
//                .add(market.toMap())
//                .await()
//
//            Result.success(documentRef.id)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

    // Más operaciones CRUD...
}

// Extensión para convertir Market a Map (Firestore)
//fun MarketEntity.toMap(): Map<String, Any> = hashMapOf(
//    "name" to name,
//    "district" to district
//)