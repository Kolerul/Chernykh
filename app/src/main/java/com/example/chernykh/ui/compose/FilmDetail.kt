package com.example.chernykh.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.chernykh.R
import com.example.chernykh.domain.model.Film
import com.example.chernykh.presentation.sceen_state.ListScreenState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailsScreen(
    filmIndex: Int,
    screenState: ListScreenState?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){


    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPaddings ->


        when(screenState){

            is ListScreenState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(innerPaddings)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(screenState.films[filmIndex].posterUrl)
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.ic_image_not_found),
                        placeholder = painterResource(id = R.drawable.placeholder),
                        contentDescription = stringResource(id = R.string.film_preview)
                    )
                    FilmDetails(
                        film = screenState.films[filmIndex]
                    )
                }
            }

            else -> {throw RuntimeException()}
        }

    }
}

@Composable
fun FilmDetails(
    film: Film,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = film.nameRu,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = film.description.ifEmpty { stringResource(id = R.string.no_description) },
            color = Color(0xFF666666)
        )
        Row {
            Text(
                text = stringResource(id = R.string.genres),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF666666)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = film.genres.map { genre -> genre.title }.joinToString(separator = ", "),
                color = Color(0xFF666666)
            )
        }
        Row {
            Text(
                text = stringResource(id = R.string.countries),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF666666)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = film.countries.map { country -> country.name }.joinToString(separator = ", "),
                color = Color(0xFF666666)
            )
        }
    }
}