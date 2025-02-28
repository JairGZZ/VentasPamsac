package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


data class ClientEntity(

    var idClient: Int = 0,
    var name: String,
    var lastName: String,
    var phone: String,
    var storeNumber: String,
    var idMarket: Int
)
