package com.example.nguyennotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nguyennotes.model.Note
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel: ViewModel() {
    val _listNote: MutableStateFlow<ArrayList<Note>> = MutableStateFlow(arrayListOf())
    val listNote: StateFlow<ArrayList<Note>> = _listNote.asStateFlow()
    private val database = Firebase.database.reference
    fun addNoteToDatabase(title: String, description: String, username: String) {
        database.child(username).push().setValue(Note(title, description))
    }
     fun clearDataList() {
        if (_listNote.value.isNotEmpty()) {
            _listNote.value.clear()
        }
    }
     fun getNoteFromDatabase(username: String) {
        database.child(username).get().addOnSuccessListener {
            for (item in it.children) {
                val note = item.getValue(Note::class.java)
                if (note != null) {
                    _listNote.value.add(note)
                }
            }
        }.addOnCanceledListener {
            println("Error")
        }
    }
}