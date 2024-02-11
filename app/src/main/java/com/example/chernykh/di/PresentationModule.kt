package com.example.chernykh.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chernykh.presentation.ViewModelFactory
import com.example.chernykh.presentation.viewmodel.CompilationFilmViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CompilationFilmViewModel::class)
    fun bindCompilationFilmViewModel(viewModel: CompilationFilmViewModel): ViewModel
}