package com.jair.ventaspamsac.domain.usecases.note

import com.jair.ventaspamsac.data.database.repository.NoteRepository
import javax.inject.Inject

class GetNotesByClientId @Inject constructor(private val noteRepository: NoteRepository)  {
     operator fun invoke(clientId: Int) = noteRepository.getNotesByClientId(clientId)


}