package com.jair.ventaspamsac.domain.items

import com.jair.ventaspamsac.data.database.entities.NoteEntity

data class NoteItem(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val clientId: Int

)
fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        id = idNote,
        title = title,
        content = description,
        date = date,
        clientId = idClient
    )
}
fun NoteItem.toNoteEntity(): NoteEntity {
    return NoteEntity(
        idNote = id,
        title = title,
        description = content,
        date = date,
        idClient = clientId
    )
}
