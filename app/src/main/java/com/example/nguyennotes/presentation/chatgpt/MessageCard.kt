package com.example.nguyennotes.presentation.chatgpt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatChunk
import com.aallam.openai.api.chat.ChatDelta
import com.example.nguyennotes.domain.model.Chat

@OptIn(BetaOpenAI::class)
@Composable
fun MessageCard (message: Chat?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp)) {
        Card(modifier = Modifier.widthIn(max = 340.dp)) {
            Text(text = message?.content ?: "", modifier = Modifier.padding(8.dp))
        }
        Text(text = message?.role?.role ?: "")
    }
}