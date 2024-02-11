package com.example.chernykh.di

import android.content.Context
import androidx.room.Room
import com.example.chernykh.data.db.FilmDatabase
import com.example.chernykh.data.db.dao.FilmDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFilmDatabase(context: Context): FilmDatabase =
        Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            "Favourite film database"
        ).build()

    @Provides
    fun provideFilmDao(db: FilmDatabase): FilmDao = db.getFilmDao()
}