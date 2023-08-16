package com.example.nguyennotes.data.repository

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.nguyennotes.di.OpenApiModule
import com.example.nguyennotes.domain.repository.ChatGPTRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatGPTRepositoryImpl @Inject constructor(
    val api: OpenAI
): ChatGPTRepository{
    @OptIn(BetaOpenAI::class)
    override suspend fun createCompletion(content: String): Flow<ChatCompletionChunk> {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a helpful assistant!"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = content
                )
            )
        )
        return api.chatCompletions(chatCompletionRequest)
    }

}