package com.example.nguyennotes.domain.model

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatChunk
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.example.nguyennotes.domain.model.note.Note

class ListChatState @OptIn(BetaOpenAI::class) constructor(val isLoading: Boolean = false,
                                                          var chatList: List<ChatChunk> = emptyList(),
                                                          val error: String = "") {
}