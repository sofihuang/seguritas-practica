package com.example.seguritaspractica.domain.usecase

import com.example.seguritaspractica.domain.model.Punto
import com.example.seguritaspractica.domain.repository.EscenarioRepository


class UpdatePunto(private val repository: EscenarioRepository) {
    operator fun invoke(puntos: List<Punto>, updatedPunto: Punto): List<Punto> {
        return repository.updatePunto(puntos, updatedPunto)
    }
}

