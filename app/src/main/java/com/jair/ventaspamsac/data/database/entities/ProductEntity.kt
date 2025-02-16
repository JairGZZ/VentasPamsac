package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductEntity.TABLE_NAME)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_product")
    var idProduct: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double
) {
    companion object {
        const val TABLE_NAME = "products"
    }
}

