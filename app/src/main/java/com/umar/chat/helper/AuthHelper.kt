package com.umar.chat.helper

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.umar.chat.utils.FcmUtils

object AuthHelper {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /** Get current Firebase user, null if not logged in */
    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    /** Get current UID, null if not logged in */
    fun getCurrentUserId(): String? = auth.currentUser?.uid

    /** Call this when user logs in or token is refreshed */
    fun sendFcmTokenToServer(context: Context, token: String) {}

    /**
     * Handles Firebase login after Google Sign-In.
     * Calls [onSuccess] when login is successful.
     */
    fun handleGoogleSignIn(
        googleIdToken: String,
        onSuccess: (FirebaseUser) -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(task.result?.user!!)
                } else {
                    onError(task.exception ?: Exception("Unknown Firebase error"))
                }
            }
    }

    /**
     * Handles post-login tasks for any FirebaseUser.
     * Checks if first-time login, posts FCM token, and optionally navigates.
     */
    fun handlePostLogin(
        context: Context,
        user: FirebaseUser,
        onNavigate: () -> Unit = {}
    ) {
        val isNewUser = user.metadata?.creationTimestamp == user.metadata?.lastSignInTimestamp

        if (isNewUser) {
            Toast.makeText(context, "Welcome, new user!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
        }

        // Always fetch FCM token and send to server
        FcmUtils.fetchFcmToken { token ->
            // TODO: Send token to server
        }

        onNavigate()
    }
}