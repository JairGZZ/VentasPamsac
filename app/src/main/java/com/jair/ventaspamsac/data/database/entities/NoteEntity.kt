package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo
import com.google.firebase.Timestamp


data class NoteEntity(
    var idNote : Int = 0,
    var title: String = "",
    var description: String = "",
    var date: Timestamp = Timestamp.now(),
)
fun NoteEntity.toMap() : Map<String, Any> = hashMapOf(
    "title" to title,
    "description" to description,
    "date" to date
)