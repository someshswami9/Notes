package com.innovatelabs.notes

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow


class NoteReposatory (private val NoteDao: NoteDao) {
    // Room executes all queries on a separate thread.
// Observed Flow will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = NoteDao.getAllNotes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
// implement anything else to ensure we're not doing long running database work
// off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(Note: Note) {
        NoteDao.insert(Note)
    }

    suspend fun delete(Note: Note){
        NoteDao.delete(Note)
    }
}