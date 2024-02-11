package com.example.chernykh.domain.model

import com.example.chernykh.data.network.model.Country
import com.example.chernykh.data.network.model.Genre

data class Film(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val type: String,
    val year: String,
    val description: String,
    val filmLength: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String,
    val ratingVoteCount: Int,
    val posterUrl: String,
    val posterUrlPreview: String
)