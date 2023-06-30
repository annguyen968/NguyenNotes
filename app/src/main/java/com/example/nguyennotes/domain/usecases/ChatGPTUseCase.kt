package com.example.nguyennotes.domain.usecases

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.example.nguyennotes.domain.model.ListChatState
import com.example.nguyennotes.domain.model.ResponseState
import com.example.nguyennotes.domain.repository.ChatGPTRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChatGPTUseCase @Inject constructor(private val repository: ChatGPTRepository) {
    @OptIn(BetaOpenAI::class)
    operator fun invoke(content: String): Flow<ResponseState<ChatCompletionChunk>> = flow {
        try {
            emit(ResponseState.Loading())
            repository.createCompletion(content = content).collect()

        }catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "An Unexpected"))
        }catch (e: IOException){
            emit(ResponseState.Error("Error Occurred"))
        }
    }
}