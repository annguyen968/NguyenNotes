package com.example.nguyennotes.domain.repository

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionChunk
import dagger.Provides
import kotlinx.coroutines.flow.Flow


interface ChatGPTRepository {
    @OptIn(BetaOpenAI::class)
    suspend fun createCompletion(content: String): Flow<ChatCompletionChunk>
}