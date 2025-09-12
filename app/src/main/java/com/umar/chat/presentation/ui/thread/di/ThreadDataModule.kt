package com.umar.chat.presentation.ui.thread.di

import com.umar.chat.data.remote.api.ThreadApiService
import com.umar.chat.domain.repository.ThreadRepository
import com.umar.chat.presentation.ui.thread.data.ThreadRepositoryImpl
import com.umar.chat.presentation.ui.thread.data.remote.api.ThreadApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ThreadDataModule {

    @Provides
    @Singleton
    fun provideThreadApiService(client: HttpClient): ThreadApiService {
        return ThreadApiServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideThreadRepository(api: ThreadApiService): ThreadRepository {
        return ThreadRepositoryImpl(api)
    }
}