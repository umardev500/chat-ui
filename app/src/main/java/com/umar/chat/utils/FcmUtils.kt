package com.umar.chat.utils

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

object FcmUtils {
    /**
     * Fetches the current FCM token. Calls onTokenReady with the token if successful.
     * If fetching fails, logs the error.
     */
    fun fetchFcmToken(onTokenReady: (String) -> Unit) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    onTokenReady(token)
                } else {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                }
            }
    }

    suspend fun getFcmToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}