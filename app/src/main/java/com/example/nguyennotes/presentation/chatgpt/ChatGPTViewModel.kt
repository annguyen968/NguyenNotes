package com.example.nguyennotes.presentation.chatgpt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.example.nguyennotes.data.repository.ChatGPTRepositoryImpl
import com.example.nguyennotes.domain.model.ListChatState
import com.example.nguyennotes.domain.model.ResponseState
import com.example.nguyennotes.domain.repository.ChatGPTRepository
import com.example.nguyennotes.domain.usecases.ChatGPTUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(BetaOpenAI::class)
@HiltViewModel
class ChatGPTViewModel @Inject constructor(
    private val repository: ChatGPTRepository
): ViewModel() {

    private val chatValue = MutableStateFlow(ListChatState())
    var _chatValue: StateFlow<ListChatState> = chatValue

    fun createCompletion(content: String) = viewModelScope.launch(Dispatchers.IO){
        repository.createCompletion(content = content).collect{
            chatValue.value.chatList = it.choices ?: emptyList()
        }

    }
}