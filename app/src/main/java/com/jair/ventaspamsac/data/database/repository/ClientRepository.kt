package com.jair.ventaspamsac.data.database.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.google.firebase.firestore.FirebaseFirestore
import com.jair.ventaspamsac.data.database.entities.ClientEntity
import com.jair.ventaspamsac.data.database.entities.MarketEntity
import com.jair.ventaspamsac.data.database.entities.toMap
import com.jair.ventaspamsac.domain.usecases.auth.GetCurrentUserUseCase
import com.jair.ventaspamsac.ui.clients.ClientsActivity
import kotlinx.coroutines.tasks.await


import javax.inject.Inject

class ClientRepository @Inject constructor(private val firestore: FirebaseFirestore, private val getCurrentUserUseCase: GetCurrentUserUseCase) {

    suspend fun getByMarket(marketId: String): List<ClientEntity> {
        val currentUser = getCurrentUserUseCase()
        if (currentUser == null) {
            Log.e("Firestore", "Usuario no autenticado")
            return emptyList()
        }
        return try {
            val snapshot = firestore.collection("users") // Accede a la colección users
                .document(currentUser.id) // Accede al documento del usuario autenticado
                .collection("markets") // Accede a la subcolección markets del usuario
                .document(marketId) // Accede al documento del market específico
                .collection("clients") // Accede a la subcolección clients del market
                .get() // Obtiene todos los documentos en la subcolección
                .await()
            // Convierte los documentos en objetos ClientEntity
            snapshot.documents.mapNotNull { document ->
                try {
                   val client = document.toObject(ClientEntity::class.java)
                    if (client != null) {
                        ClientEntity(
                            idClient = document.id, // Incluye el ID del documento
                            name = client.name,
                            lastName = client.lastName,
                            phone = client.phone,
                            storeNumber = client.storeNumber
                        )
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    Log.e("Firestore", "Error al deserializar el documento", e)
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Error al obtener los clientes del market", e)
            emptyList()
        }
    }

    suspend fun insertClient(marketId: String, client: ClientEntity) {
        val currentUser = getCurrentUserUseCase()
        if (currentUser == null) {
            Log.e("Firestore", "Usuario no autenticado")
            return
        }
        try {
            firestore.collection("users") // Accede a la colección users
                .document(currentUser.id) // Accede al documento del usuario autenticado
                .collection("markets") // Accede a la subcolección markets del usuario
                .document(marketId) // Accede al documento del market específico
                .collection("clients") // Accede a la subcolección clients del market
                .add(client.toMap()) // Agrega un nuevo documento en la subcolección clients
                .await()
            Log.d("Firestore", "Cliente insertado correctamente en la subcolección clients")
        } catch (e: Exception) {
            Log.e("Firestore", "Error al insertar cliente en Firestore", e)
        }
        //    }
//    suspend fun update(clientItem: ClientItem) {
//        clientDAO.update(clientItem.toClientEntity())
//    }

    }
}
