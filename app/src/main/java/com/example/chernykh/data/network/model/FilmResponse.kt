package com.example.chernykh.data.network.model

data class FilmResponse(
    val keyword: String,
    val pageCount: Int,
    val searchFilmsCountResult: Int,
    val films: List<FilmKP>
)