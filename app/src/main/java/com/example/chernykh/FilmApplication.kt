package com.example.chernykh

import android.app.Application
import com.example.chernykh.di.AppComponent
import com.example.chernykh.di.DaggerAppComponent

class FilmApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}