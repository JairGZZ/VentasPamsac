package com.jair.ventaspamsac.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jair.ventaspamsac.data.database.entities.NoteEntity

@Dao
interface NoteDAO  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE id_client = :clientId ORDER BY date DESC")
    fun getNotesByClientId(clientId: Int): LiveData<List<NoteEntity>>

    @Update
    suspend fun updateNote(note: NoteEntity)




}