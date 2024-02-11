package com.example.chernykh.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.chernykh.R
import com.example.chernykh.data.network.model.Country
import com.example.chernykh.data.network.model.Genre
import com.example.chernykh.domain.model.Film
import com.example.chernykh.presentation.sceen_state.ListScreenState

@Composable
fun FilmListScreen(
    popularScreenState: ListScreenState?,
    favouriteScreenState: ListScreenState?,
    updateFunction: () -> Unit,
    getFunction: () -> Unit,
    addToFavourite: (Film) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    var isPopular by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        if (isPopular){
            when (popularScreenState) {
                is ListScreenState.Initialization -> {
                    updateFunction()
                }

                is ListScreenState.Success -> {

                    FilmList(
                        list = popularScreenState.films,
                        onItemClick,
                        addToFavourite,
                        modifier.weight(1f)
                    )
                }

                is ListScreenState.Loading -> {
                    CircularProgressBarScreen(
                        modifier = modifier.weight(1f)
                    )
                }

                is ListScreenState.Error -> {
                    ErrorScreen(
                        icon = painterResource(id = popularScreenState.errorIconCode),
                        errorMessage = stringResource(id = popularScreenState.errorMessageCode),
                        onClick = updateFunction,
                        modifier = modifier.weight(1f)
                    )
                }

                else -> {throw RuntimeException()}
            }
        }else{
            when (favouriteScreenState) {
                is ListScreenState.Initialization -> {
                    getFunction()
                }

                is ListScreenState.Success -> {

                    FilmList(
                        list = favouriteScreenState.films,
                        onItemClick,
                        addToFavourite,
                        modifier.weight(1f)

                    )
                }

                is ListScreenState.Loading -> {
                    CircularProgressBarScreen(
                        modifier = modifier.weight(1f)
                    )
                }

                is ListScreenState.Error -> {
                    ErrorScreen(
                        icon = painterResource(id = favouriteScreenState.errorIconCode),
                        errorMessage = stringResource(id = favouriteScreenState.errorMessageCode),
                        onClick = updateFunction,
                        modifier = modifier.weight(1f)
                    )
                }

                else -> {throw RuntimeException()}
            }
        }


        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isPopular = !isPopular },
                enabled = !isPopular
            ) {
                Text(
                    text = stringResource(id = R.string.popular),
                    fontSize = 16.sp
                )
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isPopular = !isPopular },
                enabled = isPopular
            ) {
                Text(
                    text = stringResource(id = R.string.favourite),
                    fontSize = 16.sp
                )
            }
        }
    }

}


@Composable
fun FilmList(
    list: List<Film>,
    onItemClick: (Int) -> Unit,
    onStarClick: (Film) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        items(list.size){ index ->
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                )
            ) {
                Surface(
                    onClick = {onItemClick(index)},
                    color = Color.White
                ) {
                    FilmItem(
                        film = list[index],
                        onStarClick
                    )
                }
            }

        }
    }
}

@Composable
fun FilmItem(
    film: Film,
    onStarClick: (Film) -> Unit,
    modifier: Modifier = Modifier
){
            Row(
                modifier = modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(40.dp)
                        .height(63.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(film.posterUrlPreview)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.ic_image_not_found),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentDescription = stringResource(id = R.string.film_preview)
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = film.nameRu,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = {onStarClick(film)}) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = Icons.Rounded.Star,
                                contentDescription = stringResource(id = R.string.favourite),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row {
                        Text(
                            text = film.genres.first().title,
                            fontSize = 14.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "(${film.year})"
                        )
                    }
                }
    }
}



private val film = Film(
    1,
    "Tekken 8",
    "Tekken 8",
    "type",
    "2024",
    "Best movie ever",
    "1000 лет",
    listOf(Country("Япония")),
    listOf(Genre("Боевик")),
    "10",
    10,
    "image",
    "image"
)

@Composable
@Preview(
    showBackground = true
)
fun ShowFilmDetails(){
    FilmDetails(film = film)
}

@Composable
@Preview(
    showBackground = true
)
fun ShowFilmList(){
    FilmList(list = listOf(film, film, film), onItemClick = {}, {})
}

@Composable
@Preview
fun ShowFilmItem(){
    FilmItem(film, {})
}