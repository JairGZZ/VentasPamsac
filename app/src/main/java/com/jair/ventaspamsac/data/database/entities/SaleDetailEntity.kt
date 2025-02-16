package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = SaleDetailEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["id_sale"],
            childColumns = ["id_sale"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id_product"],
            childColumns = ["id_product"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_sale"]), Index(value = ["id_product"])]
)
data class SaleDetailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_sale_detail")
    var idSaleDetail: Int = 0,
    @ColumnInfo(name = "id_sale")
    val idSale: Int,
    @ColumnInfo(name = "id_product")
    val idProduct: Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "subtotal")
    val subtotal: Double
) {

    companion object {

        const val TABLE_NAME = "sale_details"
    }
}


