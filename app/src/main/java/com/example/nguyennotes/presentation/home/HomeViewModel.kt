package com.example.nguyennotes.presentation.home

import androidx.lifecycle.ViewModel
import com.example.nguyennotes.domain.repository.NoteRepository
import com.example.nguyennotes.domain.usecases.NoteListUseCase
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    val usecase: NoteListUseCase
): ViewModel() {

}