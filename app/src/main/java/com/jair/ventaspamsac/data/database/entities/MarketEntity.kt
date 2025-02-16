package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = MarketEntity.TABLE_NAME
)
data class MarketEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_market")
    var idMarket: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "district")
    val district: String
) {
    companion object {
        const val TABLE_NAME = "markets"
    }
}
