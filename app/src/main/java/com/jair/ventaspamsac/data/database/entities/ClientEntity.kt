package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ClientEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = MarketEntity::class,
            parentColumns = ["id_market"],
            childColumns = ["id_market"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_market"])]
)
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_client")
    var idClient: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone_client")
    val phone: String,
    @ColumnInfo(name = "email_client")
    val email: String,
    @ColumnInfo(name = "id_market") val
    idMarket: Int
) {
    companion object {
        const val TABLE_NAME = "clients"
    }
}
