package com.umar.chat.presentation.common.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umar.chat.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    fun login(idToken: String): Deferred<Boolean> {
        return viewModelScope.async {
            _isLoading.value = true
            try {
                val response = repository.login(idToken)
                Log.d("API", "Login response $response")
                true
            } catch (e: Exception) {
                Log.d("API", "Login error $e")
                e.printStackTrace()
                false
            } finally {
                _isLoading.value = false
            }
        }
    }
}