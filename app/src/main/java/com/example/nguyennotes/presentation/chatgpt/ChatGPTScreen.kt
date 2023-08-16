package com.example.nguyennotes.presentation.chatgpt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aallam.openai.api.BetaOpenAI

@OptIn(BetaOpenAI::class)
@Composable
fun ChatGPTScreen(
    viewmodel: ChatGPTViewModel = hiltViewModel()
) {
    val chatListState = viewmodel._chatValue.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffFBE9E7))
    ) {
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(), reverseLayout = true
        ) {
            items(chatListState.value.chatList.size) {
                MessageCard(message = chatListState.value.chatList[(chatListState.value.chatList.size -1) - it])
            }
        }
        MessageInput(onSubmit = {
            viewmodel.createCompletion(it)
        })
    }

}