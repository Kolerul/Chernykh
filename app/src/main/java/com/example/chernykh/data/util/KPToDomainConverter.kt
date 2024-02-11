package com.example.chernykh.data.util

import com.example.chernykh.data.network.model.FilmKP
import com.example.chernykh.domain.model.Film

fun filmKPConvertToFilm(filmKP: FilmKP): Film =
    Film(
        filmId = filmKP.filmId,
        nameRu = filmKP.nameRu,
        nameEn = filmKP.nameEn ?: "",
        type = filmKP.type ?: "",
        year = filmKP.year ?: "",
        description = filmKP.description ?: "",
        filmLength = filmKP.filmLength ?: "",
        countries = filmKP.countries ?: emptyList(),
        genres = filmKP.genres ?: emptyList(),
        rating = filmKP.rating ?: "",
        ratingVoteCount = filmKP.ratingVoteCount,
        posterUrl = filmKP.posterUrl ?: "",
        posterUrlPreview = filmKP.posterUrlPreview ?: ""
    )

fun filmKPListConvertToFilm(list: List<FilmKP>): List<Film> =
    list.map { filmKP ->  filmKPConvertToFilm(filmKP) }.toList()