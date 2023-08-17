package com.example.nguyennotes.di

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.example.nguyennotes.data.repository.ChatGPTRepositoryImpl
import com.example.nguyennotes.domain.repository.ChatGPTRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
object OpenApiModule {
    @Provides
    @Singleton
    fun providerOpenAI(): OpenAI {
        val config = OpenAIConfig(
            token = "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",
            timeout = Timeout(socket = 60.seconds),
            // additional configurations...
        )
        return OpenAI(config)
    }

    @Provides
    @Singleton
    fun provideRepository(api: OpenAI): ChatGPTRepository {
        return ChatGPTRepositoryImpl(api)
    }
}