package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = NoteEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id_client"],
            childColumns = ["id_client"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_client"])]

)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_note")
    var idNote: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "id_client")
    val idClient: Int
){
    companion object{
        const val TABLE_NAME = "notes"

    }
}