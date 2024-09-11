package com.example.seguritaspractica.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.seguritaspractica.domain.Escenario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Composable
fun ImageDetailScreen(escenarioJson: String) {
    val escenarioType = object : TypeToken<Escenario>() {}.type
    val escenario: Escenario = Gson().fromJson(escenarioJson, escenarioType)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.LightGray)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(escenario.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            escenario.puntos.forEach { punto ->
                Box(
                    modifier = Modifier
                        .offset(punto.coordenada.x.dp, punto.coordenada.y.dp)
                        .size(26.dp)
                        .background(Color.Cyan)
                ) {
                    Text(
                        text = punto.id.toString(),
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black
                    )
                }
            }
        }
    }
}
