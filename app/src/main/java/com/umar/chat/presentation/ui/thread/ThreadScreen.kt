package com.umar.chat.presentation.ui.thread

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.umar.chat.Routes
import com.umar.chat.helper.AuthHelper
import com.umar.chat.presentation.common.components.UsersBottomSheet
import com.umar.chat.presentation.navigation.ChatNavArg
import com.umar.chat.presentation.navigation.ChatType
import com.umar.chat.presentation.ui.thread.components.ThreadAppBar
import com.umar.chat.presentation.ui.thread.components.ThreadList
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ThreadScreen @Inject constructor() : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ThreadViewModel>()
        val threads = screenModel.threads.collectAsState()
        var showUsersSheet by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
        val coroutineScope = rememberCoroutineScope()

        val user = AuthHelper.getCurrentUser()
        LaunchedEffect(user) {
            user?.let {
                try {
                    val result = it.getIdToken(true).await()
                    val idToken = result.token
                    Log.d("auth", "idToken: $idToken : ${user.displayName}")
                } catch (e: Exception) {
                    Log.e("auth", "getIdToken failed: ${e.message}")
                }
            }
        }

        Scaffold(
            topBar = {
                ThreadAppBar(
                    onSearchClick = {
                        showUsersSheet = true
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .imePadding()
            ) {
                ThreadList(
                    threads = threads.value,
                    onThreadClick = { thread ->
                        val arg = ChatNavArg(
                            type = ChatType.USER,
                            threadId = thread.id,
                            name = thread.title,
                            photoUrl = thread.image
                        )
                        navigator.push(Routes.chat(arg))
                    },
                    modifier = Modifier
                        .weight(1f)
                )
            }

            if (showUsersSheet) {
                UsersBottomSheet(
                    sheetState = sheetState,
                    modifier = Modifier.statusBarsPadding(),
                    onUserClick = { user ->
                        coroutineScope.launch {
                            sheetState.hide()
                        }

                        val arg = ChatNavArg(
                            type = ChatType.USER,
                            name = user.name,
                            photoUrl = user.avatarUrl
                        )
                        navigator.push(Routes.chat(arg))
                    }
                ) {
                    showUsersSheet = false
                }
            }
        }
    }
}