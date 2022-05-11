package com.innovatelabs.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), onNoteClicked{
     private lateinit var viewModel: NoteViewModel
     private lateinit var mAdapter : NoteAdapter
    lateinit var inputText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById<EditText>(R.id.inputNoteEditText)
        val noteRecyclerView = findViewById<RecyclerView>(R.id.noteRecyclerView)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = NoteAdapter(this,this)
        noteRecyclerView.adapter = mAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {List->
            List?. let{
                mAdapter.update(it)
            }

        })

    }

    override fun onItemClicked(item: Note) {
        viewModel.deleteNote(item)
    }

    fun saveNote(view : View) {
        val inputText = inputText.text.toString()
        if (inputText.isNotEmpty()) {
            viewModel.insertNote(Note(inputText))
            Toast.makeText(this,"$inputText is Added Successfully!",Toast.LENGTH_SHORT).show()
        }
    }
}