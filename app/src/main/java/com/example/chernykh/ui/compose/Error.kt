package com.example.chernykh.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chernykh.R

@Composable
fun ErrorScreen(
    icon: Painter,
    errorMessage: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = stringResource(id = R.string.error_icon),
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Button(
                onClick =  onClick
            ){
                Text(
                    text = stringResource(id = R.string.try_again),
                    color = Color.White
                )
            }
        }
    }

}

@Composable
@Preview(
    showBackground = true
)
fun ShowError(){
    ErrorScreen(
        icon = painterResource(id = R.drawable.ic_no_internet_connection),
        errorMessage = "Произошла ошибка при загрузке данных, проверьте подключение к сети",
        onClick = { }
    )
}