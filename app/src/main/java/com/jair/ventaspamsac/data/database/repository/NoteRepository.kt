//package com.jair.ventaspamsac.data.database.repository
//
//import androidx.lifecycle.map
//import com.jair.ventaspamsac.data.database.dao.NoteDAO
//import com.jair.ventaspamsac.data.database.entities.NoteEntity
//import com.jair.ventaspamsac.domain.items.NoteItem
//import com.jair.ventaspamsac.domain.items.toNoteEntity
//import com.jair.ventaspamsac.domain.items.toNoteItem
//import javax.inject.Inject
//
//class NoteRepository @Inject constructor( private val noteDAO: NoteDAO) {
//     fun getNotesByClientId(clientId: Int) = noteDAO.getNotesByClientId(clientId).map { entities ->
//        entities.map { entity -> entity.toNoteItem() }
//    }
//    suspend fun insertNote(note: NoteItem) = noteDAO.insertNote(note.toNoteEntity())
//    suspend fun updateNote(note: NoteItem) = noteDAO.updateNote(note.toNoteEntity())
//
//}