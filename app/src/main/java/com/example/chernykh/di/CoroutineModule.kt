package com.example.chernykh.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineModule {
    @Provides
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}