package com.umar.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json()
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken =
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTc3NDEyMDcsImlhdCI6MTc1NzY1NDgwNywidXNlcl9pZCI6MX0.gn6JnylLM7YNMGhTsy-BEfmBd1Xx6lZ8hkB1vMTzphE"
                        BearerTokens(accessToken = accessToken, refreshToken = null)
                    }

                    // Called automatically when 401 Unauthorized happens
                    refreshTokens {
                        val newAccessToken = ""
                        BearerTokens(newAccessToken, null)
                    }
                }
            }
        }
    }
}