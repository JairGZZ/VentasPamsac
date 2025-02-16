package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DistrictEntity.TABLE_NAME)
data class DistrictEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_district")
    var idDistrict: Int = 0,
    @ColumnInfo(name = "name")
    val name: String
) {
    companion object {
        const val TABLE_NAME = "districts"
    }
}
