package com.example.chernykh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chernykh.presentation.viewmodel.CompilationFilmViewModel
import com.example.chernykh.ui.compose.FilmDetailsScreen
import com.example.chernykh.ui.compose.FilmListScreen
import com.example.chernykh.ui.theme.ChernykhTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CompilationFilmViewModel by viewModels{
        (application as FilmApplication).appComponent.viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChernykhTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val popularScreenState = viewModel.popularListScreenState.observeAsState()
                    val favouriteScreenState  = viewModel.favouriteListScreenState.observeAsState()

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "popular_film_list"){
                        composable("popular_film_list"){
                            FilmListScreen(
                                popularScreenState = popularScreenState.value,
                                favouriteScreenState = favouriteScreenState.value,
                                updateFunction = viewModel::getTopPopularFilms,
                                getFunction = viewModel::getTopFavouriteFilms,
                                onItemClick = { filmIndex ->
                                    navController.navigate("film_details/${filmIndex}")
                                },
                                addToFavourite = viewModel::addToFavourite
                            )
                        }
                        composable(
                            "film_details/{filmIndex}",
                            arguments = listOf(navArgument("filmIndex") {type = NavType.IntType})
                        ){ backStackEntry ->
                            FilmDetailsScreen(
                                filmIndex = backStackEntry.arguments?.getInt("filmIndex") ?: -100,
                                screenState = popularScreenState.value,
                                onBackClick = { navController.popBackStack()})
                        }
                    }
                }
            }
        }
    }
}
