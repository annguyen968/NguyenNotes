package com.example.nguyennotes.domain.repository

import com.example.nguyennotes.domain.model.note.Note


interface NoteRepository {
    suspend fun getListNote(page: String): List<Note>
}