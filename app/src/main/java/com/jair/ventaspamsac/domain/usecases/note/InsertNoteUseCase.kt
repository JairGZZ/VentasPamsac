package com.jair.ventaspamsac.domain.usecases.note

import com.jair.ventaspamsac.data.database.repository.NoteRepository
import com.jair.ventaspamsac.domain.items.NoteItem
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: NoteItem) = noteRepository.insertNote(note)

}