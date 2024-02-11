package com.example.chernykh.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chernykh.R
import com.example.chernykh.domain.model.Film
import com.example.chernykh.domain.repository.CompilationFilmRepository
import com.example.chernykh.presentation.sceen_state.ListScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompilationFilmViewModel @Inject constructor(
    private val repository: CompilationFilmRepository
): ViewModel() {

    private val _popularListScreenState = MutableLiveData<ListScreenState>(ListScreenState.Initialization)
    val popularListScreenState: LiveData<ListScreenState>
        get() = _popularListScreenState

    private val _favouriteListScreenState = MutableLiveData<ListScreenState>(ListScreenState.Initialization)
    val favouriteListScreenState: LiveData<ListScreenState>
        get() = _favouriteListScreenState

    fun getTopPopularFilms(){
        _popularListScreenState.value = ListScreenState.Loading
        viewModelScope.launch {
            try {
                val films = repository.getTopPopularFilms()
                _popularListScreenState.value = ListScreenState.Success(films)
            }catch (e: Exception){
                _popularListScreenState.value = ListScreenState.Error(R.string.no_internet_message, R.drawable.ic_no_internet_connection)
            }

        }
    }

    fun getTopFavouriteFilms(){
        _favouriteListScreenState.value = ListScreenState.Loading
        viewModelScope.launch {
            val films = repository.getFavouriteFilms()
            _favouriteListScreenState.value = ListScreenState.Success(films)
        }
    }

    fun addToFavourite(film: Film){
        viewModelScope.launch{
            repository.addToFavourite(film)
        }
    }

    fun deleteFromFavourite(film: Film){
        viewModelScope.launch {
            repository.removeFromFavourite(film)
        }
    }


}