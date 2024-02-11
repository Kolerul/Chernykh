package com.example.chernykh.data.repository

import com.example.chernykh.data.db.dao.FilmDao
import com.example.chernykh.data.network.api.KinopoiskApi
import com.example.chernykh.data.network.model.FilmKP
import com.example.chernykh.data.util.filmConvertToFilmDb
import com.example.chernykh.data.util.filmDBListConvertToFilm
import com.example.chernykh.data.util.filmKPConvertToFilm
import com.example.chernykh.data.util.filmKPListConvertToFilm
import com.example.chernykh.domain.model.Film
import com.example.chernykh.domain.repository.CompilationFilmRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompilationFilmRepositoryImpl @Inject constructor(
    private val service: KinopoiskApi,
    private val dao: FilmDao,
    private val dispatcher: CoroutineDispatcher
): CompilationFilmRepository {

    override suspend fun getTopPopularFilms(): List<Film> = withContext(dispatcher) {
        val response = service.getTopFilms("TOP_100_POPULAR_FILMS")
        val films = response.films.map { filmKP ->
            val f = service.getFilm(filmKP.filmId)
            FilmKP(
                filmKP.filmId,
                f.nameRu,
                f.nameEn,
                f.type,
                f.year,
                f.description,
                f.filmLength,
                f.countries,
                f.genres,
                f.rating,
                f.ratingVoteCount,
                f.posterUrl,
                f.posterUrlPreview
            )
        }
        return@withContext filmKPListConvertToFilm(films)
    }

    override suspend fun getFavouriteFilms(): List<Film> = withContext(dispatcher){
        val response = dao.getAllFilms()
        return@withContext filmDBListConvertToFilm(response)
    }



    override suspend fun addToFavourite(film: Film) = withContext(dispatcher){
        val filmDB = filmConvertToFilmDb(film)
        dao.addFilm(filmDB)
    }

    override suspend fun removeFromFavourite(film: Film) = withContext(dispatcher){
        val filmDB = filmConvertToFilmDb(film)
        dao.removeFilm(filmDB)
    }

}