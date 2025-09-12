package com.umar.chat.presentation.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.chat.Routes
import com.umar.chat.helper.AuthHelper
import com.umar.chat.presentation.common.components.GoogleSignInButton
import com.umar.chat.presentation.common.viewmodel.AuthViewModel
import com.umar.chat.utils.ToastUtil
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginScreen @Inject constructor() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val viewModel = getViewModel<AuthViewModel>()
        val isLoading = viewModel.isLoading.collectAsState()

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
        }

        fun handleLogin(token: String?) {
            if (token == null) {
                ToastUtil.show(context, "Failed to fetch ID token")
                return
            }

            coroutineScope.launch {
                val success = viewModel.login(token).await()
                if (success) {
                    navigator.push(Routes.thread())
                } else {
                    ToastUtil.show(context, "Failed to login")
                }
            }
        }

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
                                callback = ::handleLogin
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
                Spacer(modifier = Modifier.height(24.dp))
                if (isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp),
                        strokeWidth = 4.dp
                    )
                }
            }
        }
    }
}
