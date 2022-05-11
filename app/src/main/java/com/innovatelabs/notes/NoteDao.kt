package com.innovatelabs.notes

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
    interface NoteDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE, entity = Note::class)
         fun insert(note: Note)

        @Delete
        fun delete(note : Note)

        @Query("SELECT * FROM note_table ORDER BY Id ASC")
        fun getAllNotes(): LiveData<List<Note>>
    }