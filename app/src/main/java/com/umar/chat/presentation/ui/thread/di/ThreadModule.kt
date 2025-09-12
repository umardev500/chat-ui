package com.umar.chat.presentation.ui.thread.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.umar.chat.presentation.ui.thread.ThreadViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
interface ThreadModule {
    @Binds
    @IntoMap
    @ScreenModelKey(ThreadViewModel::class)
    fun bindThreadViewModel(viewModel: ThreadViewModel): ScreenModel
}