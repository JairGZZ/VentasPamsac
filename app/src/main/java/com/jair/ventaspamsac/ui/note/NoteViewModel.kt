package com.jair.ventaspamsac.ui.note

import androidx.lifecycle.ViewModel
import com.jair.ventaspamsac.domain.usecases.note.GetNotesByClientId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel  @Inject constructor(private val  getNotesByClientId: GetNotesByClientId) : ViewModel() {
     fun getNotesByClient(clientId: Int) = getNotesByClientId(clientId)

}