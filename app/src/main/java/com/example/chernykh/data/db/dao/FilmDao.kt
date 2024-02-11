package com.example.chernykh.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chernykh.data.db.model.FilmDB

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: FilmDB)

    @Delete
    fun removeFilm(film: FilmDB)

    @Query("SELECT * FROM FILMS")
    fun getAllFilms(): List<FilmDB>
}