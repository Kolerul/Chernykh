package com.example.chernykh.domain.repository

import com.example.chernykh.domain.model.Film

interface CompilationFilmRepository {
    suspend fun getTopPopularFilms(): List<Film>

    suspend fun getFavouriteFilms(): List<Film>

    suspend fun addToFavourite(film: Film)

    suspend fun removeFromFavourite(film: Film)
}