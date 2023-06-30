package com.example.nguyennotes.data.repository

import android.provider.ContactsContract
import com.example.nguyennotes.data.datasource.NetworkApi
import com.example.nguyennotes.domain.model.note.Note
import com.example.nguyennotes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): NoteRepository{
    override suspend fun getListNote(page: String): List<Note> {
        TODO("return api.getList")
    }

}