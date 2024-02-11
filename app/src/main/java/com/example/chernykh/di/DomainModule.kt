package com.example.chernykh.di

import com.example.chernykh.data.repository.CompilationFilmRepositoryImpl
import com.example.chernykh.domain.repository.CompilationFilmRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindPopularFilmRepository(repositoryImpl: CompilationFilmRepositoryImpl): CompilationFilmRepository
}