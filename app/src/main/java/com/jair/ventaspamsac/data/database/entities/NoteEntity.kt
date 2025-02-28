package com.jair.ventaspamsac.data.database.entities

import androidx.room.ColumnInfo


data class NoteEntity(

    var idNote: Int = 0,

    var title: String,
    var description: String,
    var date: String,
    var idClient: Int
)