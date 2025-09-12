package com.umar.chat.presentation.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.chat.Routes
import com.umar.chat.helper.AuthHelper
import com.umar.chat.presentation.common.components.GoogleSignInButton
import javax.inject.Inject

class LoginScreen @Inject constructor() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current


        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GoogleSignInButton { token ->
                    AuthHelper.handleGoogleSignIn(
                        googleIdToken = token,
                        onSuccess = { user ->
                            AuthHelper.handlePostLogin(
                                context = context,
                                user,
                                onNavigate = {
                                    navigator.push(Routes.thread())
                                }
                            )
                        },
                        onError = { e ->
                            Toast.makeText(
                                context,
                                e.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}
