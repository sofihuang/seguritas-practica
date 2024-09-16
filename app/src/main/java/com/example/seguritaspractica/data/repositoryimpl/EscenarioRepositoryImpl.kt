package com.example.seguritaspractica.data.repositoryimpl

import com.example.seguritaspractica.domain.model.Escenario
import com.example.seguritaspractica.domain.model.Punto
import com.example.seguritaspractica.domain.repository.EscenarioRepository
import androidx.compose.ui.geometry.Offset

class EscenarioRepositoryImpl : EscenarioRepository {
    private val initialPuntos = mutableListOf<Punto>()

    override fun getEscenario(): Escenario {
        return Escenario(
            id = 1,
            imageUrl = "https://i.pinimg.com/564x/3e/4f/d7/3e4fd791f54c8bee5565036c2094309d.jpg",
            puntos = initialPuntos
        )
    }

    override fun addPunto(puntos: List<Punto>, id: Int, coordenada: Offset): List<Punto> {
        return puntos + Punto(id = id, coordenada = coordenada)
    }

    override fun removePunto(puntos: List<Punto>, id: Int): List<Punto> {
        return puntos.filter { it.id != id }
    }

    override fun updatePunto(puntos: List<Punto>, updatedPunto: Punto): List<Punto> {
        return puntos.map { if (it.id == updatedPunto.id) updatedPunto else it }
    }
}
