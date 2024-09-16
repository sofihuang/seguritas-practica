package com.example.seguritaspractica.domain.usecase


import com.example.seguritaspractica.domain.model.Punto
import com.example.seguritaspractica.domain.repository.EscenarioRepository
import androidx.compose.ui.geometry.Offset

class AddPunto(private val repository: EscenarioRepository) {
    operator fun invoke(puntos: List<Punto>, id: Int, coordenada: Offset): List<Punto> {
        return repository.addPunto(puntos, id, coordenada)
    }
}
