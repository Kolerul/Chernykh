package com.example.chernykh.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chernykh.data.db.dao.FilmDao
import com.example.chernykh.data.db.model.FilmDB

@Database(
    entities = [
        FilmDB::class
    ],
    version = 1
)
abstract class FilmDatabase: RoomDatabase() {

    abstract fun getFilmDao(): FilmDao
}