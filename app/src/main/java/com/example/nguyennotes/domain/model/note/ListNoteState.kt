package com.example.nguyennotes.domain.model.note

class ListNoteState(
    val isLoading: Boolean = false,
    val coinList: List<Note> = emptyList(),
    val error: String = ""
) {
}