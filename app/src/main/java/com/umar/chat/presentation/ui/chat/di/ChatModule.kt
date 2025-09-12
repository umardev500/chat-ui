package com.umar.chat.presentation.ui.chat.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.umar.chat.presentation.ui.chat.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
interface ChatModule {
    @Binds
    @IntoMap
    @ScreenModelKey(ChatViewModel::class)
    fun bindChatViewModel(viewModel: ChatViewModel): ScreenModel
}