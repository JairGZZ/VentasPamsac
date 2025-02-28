package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


data class ClientEntity(

    var idClient: String = "",
    var name: String = "",
    var lastName: String = "",
    var phone: String = "",
    var storeNumber: String = "",
)

fun ClientEntity.toMap(): Map<String, Any> = hashMapOf(
    "name" to name,
    "lastName" to lastName,
    "phone" to phone,
    "storeNumber" to storeNumber,
)

