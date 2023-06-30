package com.example.nguyennotes.di

import com.example.nguyennotes.data.datasource.NetworkApi
import com.example.nguyennotes.data.repository.NoteRepositoryImpl
import com.example.nguyennotes.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): NetworkApi {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
        httpClient.addInterceptor(logger)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(NetworkApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(api: NetworkApi): NoteRepositoryImpl {
        return NoteRepositoryImpl(api)
    }
}