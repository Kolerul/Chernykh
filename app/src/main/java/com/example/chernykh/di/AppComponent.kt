package com.example.chernykh.di

import android.content.Context
import com.example.chernykh.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        CoroutineModule::class,
        PresentationModule::class,
        DomainModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun viewModelsFactory(): ViewModelFactory
}