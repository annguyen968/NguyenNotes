package com.example.nguyennotes.domain.usecases


import com.example.nguyennotes.domain.model.ResponseState
import com.example.nguyennotes.domain.model.note.Note
import com.example.nguyennotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NoteListUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(page: String): Flow<ResponseState<List<Note>>> = flow{
        try{
            emit(ResponseState.Loading())
            val notes = repository.getListNote(page= page)
            emit(ResponseState.Success(notes))
        }catch (e: Exception){
            emit(ResponseState.Error(e.localizedMessage ?: "Error occurred"))
        }
    }
}