package com.example.chernykh.data.util

import com.example.chernykh.data.db.model.FilmDB
import com.example.chernykh.data.network.model.Country
import com.example.chernykh.data.network.model.Genre
import com.example.chernykh.domain.model.Film

fun filmConvertToFilmDb(film: Film): FilmDB{
    val countries = film.countries.map { country -> country.name }.joinToString(separator = ", ")
    val genres = film.genres.map { genre -> genre.title }.joinToString(separator = ", ")
    return FilmDB(
        film.filmId,
        film.nameRu,
        film.nameEn,
        film.type,
        film.year,
        film.description,
        film.filmLength,
        countries,
        genres,
        film.rating,
        film.ratingVoteCount,
        film.posterUrl,
        film.posterUrlPreview
    )
}

fun filmListConvertToFilmDB(list: List<Film>): List<FilmDB> =
    list.map { film -> filmConvertToFilmDb(film) }

fun filmDBConvertToFilm(filmDB: FilmDB): Film{
    val countries = filmDB.countries.split(", ").map { str -> Country(str) }
    val genres = filmDB.genres.split(", ").map { str -> Genre(str) }
    return Film(
        filmDB.filmId,
        filmDB.nameRu,
        filmDB.nameEn,
        filmDB.type,
        filmDB.year,
        filmDB.description,
        filmDB.filmLength,
        countries,
        genres,
        filmDB.rating,
        filmDB.ratingVoteCount,
        filmDB.posterUrl,
        filmDB.posterUrlPreview
    )
}

fun filmDBListConvertToFilm(list: List<FilmDB>): List<Film> =
    list.map { filmDB -> filmDBConvertToFilm(filmDB) }