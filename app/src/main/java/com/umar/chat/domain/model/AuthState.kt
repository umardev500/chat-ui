package com.umar.chat.domain.model

import com.google.firebase.auth.FirebaseUser

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val user: FirebaseUser? = null) : AuthState()
    data class Unauthenticated(val error: String? = null) : AuthState()
}