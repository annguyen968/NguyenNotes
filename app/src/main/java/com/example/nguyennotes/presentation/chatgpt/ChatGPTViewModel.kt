package com.example.nguyennotes.presentation.chatgpt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatChunk
import com.aallam.openai.api.chat.ChatRole
import com.example.nguyennotes.domain.model.Chat
import com.example.nguyennotes.domain.model.ListChatState
import com.example.nguyennotes.domain.model.ResponseState
import com.example.nguyennotes.domain.repository.ChatGPTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(BetaOpenAI::class)
@HiltViewModel
class ChatGPTViewModel @Inject constructor(
    private val repository: ChatGPTRepository
): ViewModel() {



    var listchat =  arrayListOf<Chat>()
    private val chatValue = MutableStateFlow(ListChatState())
    var _chatValue: StateFlow<ListChatState> = chatValue
    fun createCompletion(content: String) = viewModelScope.launch(Dispatchers.IO){
        appendMessage(content = content, role = ChatRole.User)
        appendMessage(role = ChatRole.Assistant, content = "")
        repository.createCompletion(content = content).collect{
            listchat.last().content += it.choices[0].delta?.content?:""
            chatValue.value = ListChatState(chatList = listchat)
        }
    }
    private fun appendMessage(content: String, role: ChatRole){
        listchat.add(Chat(role = role, content = content))
        chatValue.value = ListChatState(chatList = listchat)
    }
}