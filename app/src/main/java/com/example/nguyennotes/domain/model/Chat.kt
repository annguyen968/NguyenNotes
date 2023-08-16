package com.example.nguyennotes.domain.model

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatRole

class Chat @OptIn(BetaOpenAI::class) constructor(val role: ChatRole = ChatRole.User, var content: String = "") {
}