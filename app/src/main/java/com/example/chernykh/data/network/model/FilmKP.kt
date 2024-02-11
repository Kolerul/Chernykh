package com.example.chernykh.data.network.model

data class FilmKP(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String?,
    val type: String?,
    val year: String?,
    val description: String?,
    val filmLength: String?,
    val countries: List<Country>?,
    val genres: List<Genre>?,
    val rating: String?,
    val ratingVoteCount: Int,
    val posterUrl: String?,
    val posterUrlPreview: String?
)