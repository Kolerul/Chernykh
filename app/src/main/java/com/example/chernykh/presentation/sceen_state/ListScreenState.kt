package com.example.chernykh.presentation.sceen_state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.chernykh.domain.model.Film

sealed class ListScreenState {
    data object Initialization: ListScreenState()
    data object Loading: ListScreenState()
    data class Success(val films: List<Film>): ListScreenState()
    data class Error(@StringRes val errorMessageCode: Int, @DrawableRes val errorIconCode: Int): ListScreenState()
}