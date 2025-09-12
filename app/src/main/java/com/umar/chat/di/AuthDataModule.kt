package com.umar.chat.di

import com.umar.chat.data.remote.api.AuthApiService
import com.umar.chat.data.remote.api.AuthApiServiceImpl
import com.umar.chat.data.repository.AuthRepository
import com.umar.chat.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthDataModule {

    @Provides
    @Singleton
    fun provideAuthApiService(client: HttpClient): AuthApiService {
        return AuthApiServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApiService): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}