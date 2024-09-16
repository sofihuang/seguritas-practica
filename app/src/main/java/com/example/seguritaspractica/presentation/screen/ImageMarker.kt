package com.example.seguritaspractica.presentation.screen


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.seguritaspractica.presentation.component.Marker
import com.example.seguritaspractica.domain.model.Escenario
import com.example.seguritaspractica.domain.model.Punto

import com.google.gson.Gson
@Composable
fun ImageMarkerScreen(navController: NavController) {
    var puntos by remember { mutableStateOf(listOf<Punto>()) }
    var selectedId by remember { mutableStateOf<Int?>(null) }
    var idCounter by remember { mutableStateOf(1) }

    val escenario = Escenario(
        id = 1,
        imageUrl = "https://i.pinimg.com/564x/3e/4f/d7/3e4fd791f54c8bee5565036c2094309d.jpg",
        puntos = puntos.toMutableList()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Button(onClick = {
                puntos = puntos + Punto(
                    id = idCounter,
                    coordenada = Offset(x = 150f, y = 150f)
                )
                selectedId = idCounter
                idCounter++
            }) {
                Text("Crear Punto")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                selectedId?.let { id ->
                    puntos = puntos.filter { it.id != id }
                    selectedId = null
                }
            }) {
                Text("Borrar Punto")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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

            puntos.filter { it.id != selectedId }.forEach { punto ->
                key(punto.id) {
                    Marker(
                        punto = punto,
                        isSelected = false,
                        onPuntoUpdated = { updatedPunto ->
                            puntos = puntos.map { if (it.id == updatedPunto.id) updatedPunto else it }
                        },
                        onDragStarted = {},
                        onDragEnded = {}
                    )
                }
            }

            puntos.find { it.id == selectedId }?.let { selectedPunto ->
                key(selectedPunto.id) {
                    Marker(
                        punto = selectedPunto,
                        isSelected = true,
                        onPuntoUpdated = { updatedPunto ->
                            puntos = puntos.map { if (it.id == updatedPunto.id) updatedPunto else it }
                        },
                        onDragStarted = {},
                        onDragEnded = {}
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            puntos.forEach { punto ->
                Button(
                    onClick = { selectedId = punto.id },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Seleccionar Punto ${punto.id}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val escenarioJson = Uri.encode(Gson().toJson(escenario))
            navController.navigate("image_detail/$escenarioJson")
        }) {
            Text("Ver Detalle")
        }
    }
}

