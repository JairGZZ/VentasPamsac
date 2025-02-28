package com.jair.ventaspamsac.data.database.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.util.query
import com.google.firebase.firestore.FirebaseFirestore
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.data.database.repository.states.AuthResult
import com.jair.ventaspamsac.domain.usecases.auth.GetCurrentUserUseCase
import kotlinx.coroutines.tasks.await
import java.io.Console

import javax.inject.Inject

class MarketRepository @Inject constructor(private val firestore: FirebaseFirestore, private val getCurrentUserUseCase: GetCurrentUserUseCase) {

    suspend fun insertMarket(market: MarketEntity) {
        val currentUser = getCurrentUserUseCase()
        if (currentUser == null) {
            Log.e("Firestore", "Usuario no autenticado")
            return
        }

        Log.d("Firestore", "Usuario autenticado con ID: ${currentUser.id}")
        try {
            firestore.collection("users") // Accede a la colección users
                .document(currentUser.id) // Accede al documento del usuario autenticado
                .collection("markets") // Accede a la subcolección markets del usuario
                .add(market.toMap()) // Agrega un nuevo documento en la subcolección
                .await()
            Log.d("Firestore", "Documento insertado correctamente en la subcolección markets")
        } catch (e: Exception) {
            Log.e("Firestore", "Error al insertar documento en Firestore", e)
        }
    }

    suspend fun getAllMarkets(): List<MarketEntity> {
            val currentUser = getCurrentUserUseCase()
            if (currentUser == null) {
                Log.e("Firestore", "Usuario no autenticado")
                return emptyList()
            }

            return try {
                val snapshot = firestore.collection("users") // Accede a la colección users
                    .document(currentUser.id) // Accede al documento del usuario autenticado
                    .collection("markets") // Accede a la subcolección markets del usuario
                    .get() // Obtiene todos los documentos en la subcolección
                    .await()

                // Convierte los documentos en objetos MarketEntity
                snapshot.documents.mapNotNull { document ->
                    try {
                        document.toObject(MarketEntity::class.java)
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error al deserializar el documento", e)
                        null
                    }
                }
            } catch (e: Exception) {
                Log.e("Firestore", "Error al obtener los markets del usuario", e)
                emptyList()
            }


    }
//    suspend fun updateMarket(market: MarketItem) {
//        marketDAO.updateMarket(market.toMarketEntity())
//    }
private fun MarketEntity.toMap(): Map<String, Any> = hashMapOf(
    "name" to name,
    "district" to district
)

}