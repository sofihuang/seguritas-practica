package com.example.seguritaspractica.domain.usecase

import com.example.seguritaspractica.domain.model.Punto
import com.example.seguritaspractica.domain.repository.EscenarioRepository

class RemovePunto(private val repository: EscenarioRepository) {
    operator fun invoke(puntos: List<Punto>, id: Int): List<Punto> {
        return repository.removePunto(puntos, id)
    }
}
