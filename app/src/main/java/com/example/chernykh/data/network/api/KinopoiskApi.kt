package com.example.chernykh.data.network.api

import com.example.chernykh.data.network.model.FilmKP
import com.example.chernykh.data.network.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {


    @GET("top")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Header("x-api-key") apiKey: String = API_KEY
    ): FilmResponse

    @GET("{filmId}")
    suspend fun getFilm(
        @Path("filmId") filmId: Int,
        @Header("x-api-key") apiKey: String = API_KEY
    ): FilmKP

    companion object{
        const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }
}

//e30ffed0-76ab-4dd6-b41f-4c9da2b2735b
//ce965cf6-92c8-4614-81ce-b3ac44a02287