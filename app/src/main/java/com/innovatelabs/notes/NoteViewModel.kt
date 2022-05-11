package com.innovatelabs.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope

import com.innovatelabs.notes.NoteRoomDatabase.Companion.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNote: LiveData<List<Note>>
    val reposatory : NoteReposatory
    init {
        val dao = NoteRoomDatabase.getDatabase(application).NoteDao()
        reposatory = NoteReposatory(dao)
        allNote =  reposatory.allNotes
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        reposatory.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        reposatory.insert(note)
    }
}