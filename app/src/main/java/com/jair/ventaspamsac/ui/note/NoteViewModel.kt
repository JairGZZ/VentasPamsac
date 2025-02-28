//package com.jair.ventaspamsac.ui.note
//
//import androidx.lifecycle.ViewModel
//import com.jair.ventaspamsac.domain.items.NoteItem
//import com.jair.ventaspamsac.domain.usecases.note.GetNotesByClientId
//import com.jair.ventaspamsac.domain.usecases.note.InsertNoteUseCase
//import com.jair.ventaspamsac.domain.usecases.note.UpdateNoteUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class NoteViewModel  @Inject constructor(private val  getNotesByClientId: GetNotesByClientId
//, private val UpdateNoteUseCase: UpdateNoteUseCase
//, private val InsertNoteUseCase: InsertNoteUseCase
//) : ViewModel() {
//     fun getNotesByClient(clientId: Int) = getNotesByClientId(clientId)
//
//     suspend fun updateNote(note: NoteItem) = UpdateNoteUseCase(note)
//     suspend fun insertNote(note: NoteItem) = InsertNoteUseCase(note)
//
//
//}