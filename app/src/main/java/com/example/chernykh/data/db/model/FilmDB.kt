package com.example.chernykh.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chernykh.data.network.model.Country
import com.example.chernykh.data.network.model.Genre

@Entity(
    tableName = "Films"
)
data class FilmDB(
    @PrimaryKey
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val type: String,
    val year: String,
    val description: String,
    val filmLength: String,
    val countries: String,
    val genres: String,
    val rating: String,
    val ratingVoteCount: Int,
    val posterUrl: String,
    val posterUrlPreview: String
)