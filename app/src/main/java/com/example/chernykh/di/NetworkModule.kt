package com.example.chernykh.di

import com.example.chernykh.data.network.api.KinopoiskApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory
            .create(gson)

    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

    @Provides
    fun provideRetrofit(factory: GsonConverterFactory, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(KinopoiskApi.BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()

    @Provides
    fun provideService(retrofit: Retrofit): KinopoiskApi =
        retrofit.create()
}